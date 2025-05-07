package com.example.backlogtracker.entities;

import androidx.room.Entity;

@Entity(tableName = "xboxgames")
public class Xbox extends Game {
    private int achievements;

    public Xbox(int gameID, String gameName, String genre, int achievements) {
        super(gameID, gameName, genre);
        this.achievements = achievements;
    }

    public int getAchievements() {
        return achievements;
    }

    public void setAchievements() {
        this.achievements = achievements;
    }
}