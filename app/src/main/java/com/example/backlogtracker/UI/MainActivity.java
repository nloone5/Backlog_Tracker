package com.example.backlogtracker.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.backlogtracker.entities.Playstation;
import com.example.backlogtracker.entities.Xbox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView searchTitle = findViewById(R.id.searchTitle);
        repository = new Repository(getApplication());

        Spinner spinner = findViewById(R.id.genrePicker);
        ArrayList<String> genreArrayList = new ArrayList<>();
        genreArrayList.add("Pick a genre");
        for (Game g : repository.getAllGames()) {
            if (!genreArrayList.contains(g.getGenre())) {
                genreArrayList.add(g.getGenre());
            }
        }
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genreArrayList);
        spinner.setAdapter(genreAdapter);


        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameDetails.class);
                startActivity(intent);
            }
        });

        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = searchTitle.getText().toString().trim();
                String genre = spinner.getSelectedItem().toString();
                Intent intent = new Intent(MainActivity.this, SearchReport.class);
                if (!title.isEmpty()) {
                    intent.putExtra("title", title);
                }
                if (!genre.isEmpty()) {
                    intent.putExtra("genre", genre);
                }
                if (!title.isEmpty() || !genre.isEmpty()) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "You must enter a title or genre!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        RecyclerView recyclerView = findViewById(R.id.backlogRecyclerView);
        List<Game> allGames = repository.getAllGames();
        List<Playstation> allPlaystation = repository.getAllPlaystation();
        List<Xbox> allXbox = repository.getAllXbox();
        final GameAdapter gameAdapter = new GameAdapter(this);
        recyclerView.setAdapter(gameAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gameAdapter.setGames(allGames);
        gameAdapter.setXbox(allXbox);
        gameAdapter.setPlaystation(allPlaystation);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Game> allGames = repository.getAllGames();
        RecyclerView recyclerView = findViewById(R.id.backlogRecyclerView);
        final GameAdapter gameAdapter = new GameAdapter(this);
        recyclerView.setAdapter(gameAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gameAdapter.setGames(allGames);

        Spinner spinner = findViewById(R.id.genrePicker);
        ArrayList<String> genreArrayList = new ArrayList<>();
        genreArrayList.add("");
        for (Game g : repository.getAllGames()) {
            if (!genreArrayList.contains(g.getGenre())) {
                genreArrayList.add(g.getGenre());
            }
        }
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genreArrayList);
        spinner.setAdapter(genreAdapter);
    }
}