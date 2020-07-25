package com.example.instabugtask.model.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Ziyad on May, 2020
 */
@Entity
public class Word {
    @PrimaryKey
    @NonNull
    public String content;

    @ColumnInfo(name = "repeat_count")
    public int repeatCount;

    public Word(@NonNull String content) {
        this.content = content;
        this.repeatCount = 1;
    }

    public void incrementCount(){
        this.repeatCount++;
    }
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Word)) {
            return false;
        }
        Word w = (Word) obj;
        return w.content.equals(this.content);
    }
}
