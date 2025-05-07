package com.example.backlogtracker.UI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.backlogtracker.R;
import com.example.backlogtracker.database.Repository;
import com.example.backlogtracker.entities.Game;
import com.example.backlogtracker.entities.Playstation;
import com.example.backlogtracker.entities.Xbox;

public class GameDetails extends AppCompatActivity {
    String name;
    String genre;
    int trophies;
    int gameID;

    EditText editName;
    EditText editGenre;
    EditText editTrophies;
    Repository repository;
    Game currentGame;

    boolean playstationChecked = false;
    boolean xboxChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        repository = new Repository(getApplication());

        RadioGroup radioGroup = findViewById(R.id.consoleGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                      if (checkedId == 2131231097) {
                                                          xboxChecked = true;
                                                          playstationChecked = false;
                                                          System.out.println("Xbox");
                                                      } else if (checkedId == 2131231095) {
                                                          xboxChecked = false;
                                                          playstationChecked = true;
                                                          System.out.println("Playstation");
                                                      }
                                                  }
        });



        editName = findViewById(R.id.editTitle);
        editGenre = findViewById(R.id.editGenre);
        editTrophies = findViewById(R.id.editTrophies);
        gameID= getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        genre = getIntent().getStringExtra("genre");

        for (Playstation p : repository.getAllPlaystation()) {
            if (p.getGameID() == gameID) {
                trophies = p.getTrophies();
            }
        }
        for (Xbox x : repository.getAllXbox()) {
            if (x.getGameID() == gameID) {
                trophies = x.getAchievements();
            }
        }

        editName.setText(name);
        editGenre.setText(genre);
        editTrophies.setText(String.valueOf(trophies));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.saveGame) {
            Game game;
            Playstation playstation;
            Xbox xbox;
            if (gameID == -1) {
                if (repository.getAllGames().isEmpty()) {
                    gameID = 1;
                } else {
                    gameID = repository.getAllGames().get(repository.getAllGames().size() - 1).getGameID() + 1;
                    System.out.println(gameID);
                    game = new Game(gameID, editName.getText().toString(), editGenre.getText().toString());
                    repository.insert(game);
                /*    if (playstationChecked) {
                        System.out.println("Playstation game");
                        playstation = new Playstation(gameID, editName.getText().toString(), editGenre.getText().toString(), Integer.parseInt(editTrophies.getText().toString()));
                        repository.insert(playstation);
                        this.finish();
                    } else if (xboxChecked) {
                        System.out.println("Xbox game");
                        xbox = new Xbox(gameID, editName.getText().toString(), editGenre.getText().toString(), Integer.parseInt(editTrophies.getText().toString()));
                        repository.insert(xbox);
                        this.finish();
                    } */
                }
            } else {
                System.out.println(gameID);
                game = new Game(gameID, editName.getText().toString(), editGenre.getText().toString());
                repository.update(game);
                /*    if (playstationChecked) {
                    playstation = new Playstation(gameID, editName.getText().toString(), editGenre.getText().toString(), Integer.parseInt(editTrophies.getText().toString()));
                    repository.update(playstation);
                    this.finish();
                } else if (xboxChecked) {
                    xbox = new Xbox(gameID, editName.getText().toString(), editGenre.getText().toString(), Integer.parseInt(editTrophies.getText().toString()));
                    repository.update(xbox);
                    this.finish();
                } */
            }
        }
        return true;
    }
}