package com.example.instabugtask.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Created by Ziyad on May, 2020
 */
public class DownloadWorker extends Worker {
    public DownloadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        return null;
    }
}
