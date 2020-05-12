package com.example.instabugtask.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Ziyad on May, 2020
 */
@Database(entities = {Word.class}, version = 1)
public abstract class WordDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
}
