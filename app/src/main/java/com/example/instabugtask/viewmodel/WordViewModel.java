package com.example.instabugtask.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.instabugtask.model.room.Word;
import com.example.instabugtask.model.room.WordDatabase;
import com.example.instabugtask.util.Constants;
import com.example.instabugtask.worker.DownloadWorker;

import java.util.List;

/**
 * Created by Ziyad on May, 2020
 */
public class WordViewModel extends AndroidViewModel {
    private WorkManager workManager;
    private MutableLiveData<FetchingState> progressState = new MutableLiveData<>();
    private LiveData<List<Word>> words;
    public WordViewModel(@NonNull Application application) {
        super(application);
        workManager = WorkManager.getInstance(application.getApplicationContext());
        getWordsFromDb();
        initWorkers();
        observeWorkState();


    }

    private void getWordsFromDb() {
        words = WordDatabase.getInstance(getApplication()).wordDao().getWords();
    }

    private void initWorkers() {
        //Configuring DownloadWorker
        Constraints downloadWorkerConstraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest downloadWorker = new OneTimeWorkRequest.Builder(DownloadWorker.class)
                .addTag(Constants.DOWNLOAD_WORKER_TAG)
                .setConstraints(downloadWorkerConstraints)
                .build();

        workManager.enqueueUniqueWork(Constants.DOWNLOAD_WORKER_TAG, ExistingWorkPolicy.KEEP, downloadWorker);

    }

    private void observeWorkState() {
        workManager.getWorkInfosByTagLiveData(Constants.DOWNLOAD_WORKER_TAG).observeForever( new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                WorkInfo.State state =  workInfos.get(0).getState();
                switch (state) {
                    case FAILED:
                        progressState.postValue(FetchingState.NETWORK_ERROR);
                        break;
                    case RUNNING:
                        progressState.postValue(FetchingState.LOADING);
                        break;
                    case SUCCEEDED:
                        progressState.postValue(FetchingState.SUCCESS);
                        break;
                }
            }
        });
    }

    public LiveData<List<Word>> getWords() {
        return words;
    }

    public LiveData<FetchingState> getProgressState() {
        return progressState;
    }
}
