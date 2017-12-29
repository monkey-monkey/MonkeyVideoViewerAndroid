package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.fragment.ClockFragment;

public class ClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ClockFragment.getInstance(),"ClockFragment")
                    .commit();
        }
    }
}
