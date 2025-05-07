package com.example.backlogtracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.backlogtracker.entities.Game;

import java.util.List;

@Dao
public interface GameDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Game game);

    @Update
    void update(Game game);

    @Delete
    void delete(Game game);

    @Query("SELECT * FROM GAMES ORDER BY gameID ASC")
    List<Game> getAllGames();
}
