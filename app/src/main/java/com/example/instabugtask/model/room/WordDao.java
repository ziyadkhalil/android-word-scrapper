package com.example.instabugtask.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by Ziyad on May, 2020
 */
@Dao
public interface WordDao {
    @Query("SELECT * FROM word")
    LiveData<List<Word>> getWords();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWords(List<Word> words);

}
