package com.example.instabugtask.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instabugtask.R;
import com.example.instabugtask.model.room.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ziyad on May, 2020
 */
public class WordsListAdapter extends RecyclerView.Adapter<WordsListAdapter.ViewHolder> {

    List<Word> words = new ArrayList<>();

    @NonNull
    @Override
    public WordsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WordsListAdapter.ViewHolder holder, int position) {
        holder.wordTv.setText(words.get(position).content);
        holder.countTv.setText(String.valueOf(words.get(position).repeatCount));
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView wordTv;
        TextView countTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTv = itemView.findViewById(R.id.wordTv);
            countTv = itemView.findViewById(R.id.countTv);
        }
    }

}
