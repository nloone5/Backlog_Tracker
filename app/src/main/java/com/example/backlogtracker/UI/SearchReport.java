package com.example.backlogtracker.UI;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backlogtracker.R;
import com.example.backlogtracker.database.Repository;
import com.example.backlogtracker.entities.Game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SearchReport extends AppCompatActivity {
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_report);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy \nHH:mm:ss z", Locale.US);
        String currentDateTime = sdf.format(new Date());

        TextView searchedTitle = findViewById(R.id.searchTerm);
        TextView searchedGenre = findViewById(R.id.genreSearched);
        TextView reportDate = findViewById(R.id.dateTime);

        String name = getIntent().getStringExtra("title");
        String genre = getIntent().getStringExtra("genre");

        searchedTitle.setText(name);
        searchedGenre.setText(genre);
        reportDate.setText(currentDateTime);

        RecyclerView recyclerView = findViewById(R.id.searchRecyclerView);
        repository = new Repository(getApplication());
        final SearchAdapter searchAdapter = new SearchAdapter(this);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Game> filteredGames = new ArrayList<>();
        for (Game g : repository.getAllGames()) {
            if (name != null) {
                if (genre != null) {
                    if (g.getGameName().contains(name) && g.getGenre().equals(genre)) {
                        filteredGames.add(g);
                        System.out.println(g.getGameName());
                    }
                } else {
                    if (g.getGameName().contains(name)) {
                        filteredGames.add(g);
                        System.out.println(g.getGameName());
                    }
                }
            } else if (genre != null) {
                if (g.getGenre().equals(genre)) {
                    filteredGames.add(g);
                    System.out.println(g.getGameName());
                }
            }
        }
        searchAdapter.setGames(filteredGames);
    }
}