package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.monkey_monkey.monkeyvideoviewerandroid.R;

public class StreamVideoActivity extends AppCompatActivity {

    private static final String TAG = "StreamVideoActivity";
    String link = "http://192.168.1.121:8080/get/v1/video?v=U2FsdGVkX1%2FOhWWOr1%2FzkaIltFAyjr4jHp3vgDigpfEFEaUv2EgjiSmGUM7uARHe92wMDEOe4zHm9%2BtaMLFqiKDuM7i7ZzjAG%2FSRkFRIMiY%3D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_video);

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        VideoView videoView = findViewById(R.id.video_view);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri video = Uri.parse(getIntent().getStringExtra("link"));
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);
        videoView.start();
    }
}
