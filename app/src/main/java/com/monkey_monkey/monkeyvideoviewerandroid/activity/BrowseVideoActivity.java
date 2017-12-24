package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.fragment.BrowseVideoFragment;

public class BrowseVideoActivity extends AppCompatActivity {
    private static final String TAG = "BrowseVideoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_video);

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, BrowseVideoFragment.getInstance(), "BrowseVideoFragment")
                    .commit();
        }
    }
}
