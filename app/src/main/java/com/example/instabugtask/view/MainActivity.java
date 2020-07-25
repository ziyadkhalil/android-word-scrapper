package com.example.instabugtask.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.instabugtask.R;
import com.example.instabugtask.model.room.Word;
import com.example.instabugtask.util.Constants;
import com.example.instabugtask.viewmodel.FetchingState;
import com.example.instabugtask.viewmodel.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordViewModel viewModel;
    RecyclerView list;
    ProgressBar progressBar;
    WordsListAdapter wordsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(WordViewModel.class);
        initViews();
        observeWordsDb();
        observeFetchingState();
    }

    private void observeFetchingState() {
        viewModel.getProgressState().observe(this, new Observer<FetchingState>() {
            @Override
            public void onChanged(FetchingState fetchingState) {
                switch (fetchingState) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        break;
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        break;
                    case NETWORK_ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, Constants.NETWORK_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void observeWordsDb() {
        viewModel.getWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                wordsListAdapter.words = words;
                wordsListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initViews() {
        list = findViewById(R.id.list);
        progressBar = findViewById(R.id.progressBar);
        wordsListAdapter = new WordsListAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        list.setAdapter(wordsListAdapter);
    }
}
