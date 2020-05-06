package com.example.roomdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdemo.R;
import com.example.roomdemo.adapters.NoteAdapter;
import com.example.roomdemo.databinding.ActivityMainBinding;
import com.example.roomdemo.database.Note;
import com.example.roomdemo.viewmodel.NoteViewModel;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnDeleteClickListener {

    private static final int ACTIVITY_REQUEST_CODE = 10;
    public static final int UPDATE_REQUEST_CODE = 11;
    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpRecyclerView();
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AddNoteActivity.class);
            startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
        });

        noteViewModel.getNoteListData().observe(this, noteList -> {
            adapter.setNoteData(noteList);
        });
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            final String note_id = UUID.randomUUID().toString();
            Note note = new Note(note_id, data.getStringExtra(AddNoteActivity.NOTE_ADDED));
            noteViewModel.insert(note);
            Toast.makeText(this, "Note Saved successfully", Toast.LENGTH_LONG).show();
        } else if (requestCode == UPDATE_REQUEST_CODE && resultCode == RESULT_OK) {
            Note updateNote = new Note(data.getStringExtra(EditNoteActivity.NOTE_ID),
                    data.getStringExtra(EditNoteActivity.UPDATE_NOTE));
            noteViewModel.update(updateNote);
            Toast.makeText(this, "Note updated successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Note not Saved ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void OnDeleteClick(Note note) {
        noteViewModel.delete(note);
        Toast.makeText(this, "Deleted successfully", Toast.LENGTH_LONG).show();
    }

}
