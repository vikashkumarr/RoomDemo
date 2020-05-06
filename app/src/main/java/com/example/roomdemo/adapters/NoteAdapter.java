package com.example.roomdemo.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdemo.R;
import com.example.roomdemo.activities.EditNoteActivity;
import com.example.roomdemo.databinding.ItemViewBinding;
import com.example.roomdemo.database.Note;

import java.util.ArrayList;
import java.util.List;

import static com.example.roomdemo.activities.MainActivity.UPDATE_REQUEST_CODE;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private Context context;
    private OnDeleteClickListener listener;

    public NoteAdapter(Context context, OnDeleteClickListener listener) {
        noteList = new ArrayList<>();
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_view, parent, false);
        return new NoteViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.setNoteData(note.getNote(), position);
        holder.setListeners();
    }

    public void setNoteData(List<Note> notes) {
        this.noteList = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        ItemViewBinding itemBinding;
        private TextView noteItem;
        private ImageView deleteNote, editNote;
        private int position;

        public NoteViewHolder(ItemViewBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
        }

        public void setNoteData(String note, int position) {
            itemBinding.txvNote.setText(note);
            this.position = position;
        }

        public void setListeners() {
            itemBinding.ivRowEdit.setOnClickListener(view -> {
                Intent intent = new Intent(context, EditNoteActivity.class);
                intent.putExtra("note_id", noteList.get(position).getId());
                ((Activity) context).startActivityForResult(intent, UPDATE_REQUEST_CODE);
            });

            itemBinding.ivRowDelete.setOnClickListener(view -> {
                if (listener != null) {
                    listener.OnDeleteClick(noteList.get(position));
                }
            });
        }
    }
    public interface OnDeleteClickListener {
        void OnDeleteClick(Note note);
    }
}


