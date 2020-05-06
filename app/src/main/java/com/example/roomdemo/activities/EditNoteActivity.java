package com.example.roomdemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.roomdemo.viewmodel.EditNoteViewModel;
import com.example.roomdemo.database.Note;
import com.example.roomdemo.R;
import com.example.roomdemo.databinding.ActivityEditNoteBinding;

public class EditNoteActivity extends AppCompatActivity {

    public static final String NOTE_ID = "note_id";
    public static final String UPDATE_NOTE = "note_text";
    private ActivityEditNoteBinding binding;
    private EditNoteViewModel editNoteViewModel;
    private Bundle bundle;
    private String noteId;
    private LiveData<Note> note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note);
        editNoteViewModel = ViewModelProviders.of(this).get(EditNoteViewModel.class);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            noteId = bundle.getString("note_id");
        }

        note = editNoteViewModel.getNote(noteId);
        note.observe(this, note -> binding.edtNote.setText(note.getNote()));
    }

    public void updateNote(View view) {
        if (TextUtils.isEmpty(binding.edtNote.getText())) {
            binding.edtNote.setError("Please update your note");
        } else {
            String updateNote = binding.edtNote.getText().toString();
            Intent intent = new Intent();
            intent.putExtra(NOTE_ID, noteId);
            intent.putExtra(UPDATE_NOTE, updateNote);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void cancelUpdate(View view) {
        finish();
    }
}
