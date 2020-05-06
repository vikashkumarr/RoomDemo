package com.example.roomdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdemo.database.Note;
import com.example.roomdemo.database.NoteDao;
import com.example.roomdemo.database.NoteRoomDatabase;

public class EditNoteViewModel extends AndroidViewModel {

    private NoteDao noteDao;
    private NoteRoomDatabase database;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        database = NoteRoomDatabase.getNoteRoomDatabase(application);
        noteDao = database.getMyNoteDao();
    }

    public LiveData<Note> getNote(String noteId){
        return noteDao.getNote(noteId);
    }
}
