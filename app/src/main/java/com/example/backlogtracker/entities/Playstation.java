package com.example.backlogtracker.entities;

import androidx.room.Entity;

@Entity(tableName = "playstationgames")
public class Playstation extends Game {
    private int trophies;

    public Playstation(int gameID, String gameName, String genre, int trophies) {
        super(gameID, gameName, genre);
        this.trophies = trophies;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setTrophies() {
        this.trophies = trophies;
    }
}
