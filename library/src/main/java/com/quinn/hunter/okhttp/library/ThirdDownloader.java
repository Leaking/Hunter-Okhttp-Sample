package com.quinn.hunter.okhttp.library;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by quinn on 15/10/2018
 */
public class ThirdDownloader {

    public static void startDownload(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Response response = okHttpClient.newCall(request).execute();
                    String result = response.body().string();
                    Log.i("ThirdDownloader", "startDownload finish");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}



