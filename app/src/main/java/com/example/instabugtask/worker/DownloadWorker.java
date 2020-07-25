package com.example.instabugtask.worker;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.instabugtask.model.room.Word;
import com.example.instabugtask.model.room.WordDatabase;
import com.example.instabugtask.util.Constants;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ziyad on May, 2020
 */
public class DownloadWorker extends Worker {
    public DownloadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Result doWork() {
        try {
            URL url = new URL(Constants.INSTABUG_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            StringBuffer output = new StringBuffer();
            String str;
            while ((str = br.readLine()) != null)
                output.append(str);
            Pattern pattern = Pattern.compile("<body>(.*?)</body>");
            Matcher matcher = pattern.matcher(output.toString());
            matcher.find();
            Spanned spanned = Html.fromHtml(matcher.group(1), Html.FROM_HTML_MODE_LEGACY);
            String body = spanned.toString();
            String[] bodyWords = body.trim().split("\\s+");
            ArrayList<Word> words = new ArrayList<>();
            for ( String bodyWord: bodyWords) {
                Word w = new Word(bodyWord);
                if(words.contains(w))
                    words.get(words.indexOf(w)).incrementCount();
                else
                    words.add(w);
            }
            WordDatabase.getInstance(getApplicationContext()).wordDao().insertWords(words);
            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure();
        }
    }
}
