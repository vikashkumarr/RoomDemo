package com.example.roomdemo.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdemo.database.NoteDao;
import com.example.roomdemo.database.NoteRoomDatabase;
import com.example.roomdemo.database.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private String TAG = NoteViewModel.class.getSimpleName();
    private NoteDao noteDao;
    private NoteRoomDatabase database;
    private LiveData<List<Note>> noteListData;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        database = NoteRoomDatabase.getNoteRoomDatabase(application);
        noteDao = database.getMyNoteDao();
        noteListData = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getNoteListData() {
        return noteListData;
    }

    public void insert(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteAsyncTask(noteDao).execute(note);
    }

    private class InsertAsyncTask extends ParentAsyncTask {

        public InsertAsyncTask(NoteDao noteDao) {
            super(noteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends ParentAsyncTask {
        public UpdateAsyncTask(NoteDao noteDao) {
            super(noteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends ParentAsyncTask {
        public DeleteAsyncTask(NoteDao noteDao) {
            super(noteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private class ParentAsyncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;

        public ParentAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            return null;
        }
    }
}
