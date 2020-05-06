package com.example.roomdemo.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_note")
public class Note {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "note")
    @NonNull
    private String mNote;

    public Note(@NonNull String id, @NonNull String mNote) {
        this.id = id;
        this.mNote = mNote;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getNote() {
        return this.mNote;
    }
}
