package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.fragment.BrowseVideoFragment;
import com.monkey_monkey.monkeyvideoviewerandroid.manager.EncryptVideoPathManager;

public class BrowseVideoActivity extends AppCompatActivity implements BrowseVideoFragment.onClickListener, EncryptVideoPathManager.onLoad {
    private static final String TAG = "BrowseVideoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_video);

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            BrowseVideoFragment.getInstance().init(this);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, BrowseVideoFragment.getInstance(), "BrowseVideoFragment")
                    .commit();
        }
    }

    @Override
    public void onClick(String videoName) {
        String query = "{\"studentCode\":" + getIntent().getStringExtra("studentCode") + ",\"videoName\":\"" + videoName + "\"}";
        EncryptVideoPathManager.getInstance().encrypt(query,this);

    }

    @Override
    public void onPathReceive(String path) {
        Intent intent = new Intent(BrowseVideoActivity.this, StreamVideoActivity.class);
        intent.putExtra("link", getResources().getString(R.string.base_url) + "get/v1/video?v=" + path);
        startActivity(intent);
    }
}
