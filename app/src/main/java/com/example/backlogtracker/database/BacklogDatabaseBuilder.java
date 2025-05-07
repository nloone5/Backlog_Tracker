package com.example.backlogtracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.backlogtracker.dao.XboxDAO;
import com.example.backlogtracker.dao.GameDAO;
import com.example.backlogtracker.dao.PlaystationDAO;
import com.example.backlogtracker.entities.Game;
import com.example.backlogtracker.entities.Playstation;
import com.example.backlogtracker.entities.Xbox;

@Database(entities = {Game.class, Playstation.class, Xbox.class}, version = 1, exportSchema = false)
public abstract class BacklogDatabaseBuilder extends RoomDatabase {
    public abstract GameDAO gameDAO();
    public abstract PlaystationDAO playstationDAO();
    public abstract XboxDAO xboxDAO();
    private static volatile BacklogDatabaseBuilder INSTANCE;

    static BacklogDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BacklogDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BacklogDatabaseBuilder.class, "MyBacklogDatabase.db")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
