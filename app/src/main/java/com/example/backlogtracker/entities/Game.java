package com.example.backlogtracker.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "games")
public class Game {
    @PrimaryKey(autoGenerate = true)
    protected int gameID;
    protected String gameName;
    protected String genre;

    public Game(int gameID, String gameName, String genre) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.genre = genre;
    }

    public int getGameID() {
        return gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
