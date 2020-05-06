package com.example.roomdemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdemo.R;
import com.example.roomdemo.databinding.ActivityAddNoteBinding;

public class AddNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "note_added";
    private ActivityAddNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);

        binding.btnSaveNote.setOnClickListener(view -> {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(binding.edtAddNote.getText())) {
                binding.edtAddNote.setError("Please enter your note");
                setResult(RESULT_CANCELED, intent);
                if (!TextUtils.isEmpty(binding.edtAddNote.getText())){
                    finish();
                }
            } else {
                String note = binding.edtAddNote.getText().toString();
                intent.putExtra(NOTE_ADDED, note);
                setResult(RESULT_OK,intent);
                finish();
            }
            //finish();
        });

    }
}
