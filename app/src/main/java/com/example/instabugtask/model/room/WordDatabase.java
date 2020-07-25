package com.example.instabugtask.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Ziyad on May, 2020
 */
@Database(entities = {Word.class}, version = 1)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase roomDatabase;
    public abstract WordDao wordDao();
    public static WordDatabase getInstance(Context context) {
        if(roomDatabase == null) {
            roomDatabase = Room.databaseBuilder(context, WordDatabase.class, "words_database").build();
        }
        return roomDatabase;
    }
}
