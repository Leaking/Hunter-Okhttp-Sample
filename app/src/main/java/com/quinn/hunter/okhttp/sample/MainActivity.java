package com.quinn.hunter.okhttp.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hunter.library.okhttp.OkHttpHooker;
import com.quinn.hunter.okhttp.library.ThirdDownloader;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);
        OkHttpHooker.installEventListenerFactory(CustomGlobalEventListener.FACTORY);
        OkHttpHooker.installDns(new CustomGlobalDns());
        OkHttpHooker.installInterceptor(new CustomGlobalInterceptor());
        run("http://square.github.io/okhttp/");
        run("https://github.com/");
        ThirdDownloader.startDownload("https://www.v2ex.com/");
    }

    void run(final String url) {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}