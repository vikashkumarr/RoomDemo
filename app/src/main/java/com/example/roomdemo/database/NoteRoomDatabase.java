package com.example.roomdemo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, version = 2)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDao getMyNoteDao();

    private static NoteRoomDatabase noteRoomDatabase;

    public static NoteRoomDatabase getNoteRoomDatabase(Context context) {
        if (noteRoomDatabase == null) {
            synchronized (NoteRoomDatabase.class) {
                if (noteRoomDatabase == null) {
                    noteRoomDatabase = Room.databaseBuilder(context.getApplicationContext(), NoteRoomDatabase.class, "myNote_database").build();
                }
            }
        }
        return noteRoomDatabase;
    }

}
