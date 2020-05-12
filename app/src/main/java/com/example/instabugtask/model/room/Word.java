package com.example.instabugtask.model.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Ziyad on May, 2020
 */
@Entity
public class Word {
    @PrimaryKey
    public String content;

    @ColumnInfo(name = "repeat_count")
    public int repeatCount;
}
