package com.example.backlogtracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.backlogtracker.entities.Playstation;

import java.util.List;

@Dao
public interface PlaystationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Playstation playstation);

    @Update
    void update(Playstation playstation);

    @Delete
    void delete(Playstation playstation);

    @Query("SELECT * FROM playstationgames ORDER BY gameID ASC")
    List<Playstation> getAllPlaystation();
}
