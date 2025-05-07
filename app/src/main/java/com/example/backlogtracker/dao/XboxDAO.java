package com.example.backlogtracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.backlogtracker.entities.Xbox;

import java.util.List;

@Dao
public interface XboxDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Xbox xbox);

    @Update
    void update(Xbox xbox);

    @Delete
    void delete(Xbox xbox);

    @Query("SELECT * FROM XBOXGAMES ORDER BY achievements ASC")
    List<Xbox> getAllXbox();
}
