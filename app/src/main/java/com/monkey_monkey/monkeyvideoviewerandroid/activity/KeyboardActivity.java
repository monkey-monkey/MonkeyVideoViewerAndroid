package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.fragment.KeyboardFragment;

public class KeyboardActivity extends AppCompatActivity implements KeyboardFragment.onRequestActivityChangeListener {

    private static final String TAG = "KeyboardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        if (savedInstanceState == null){
            KeyboardFragment.getInstance().init(this);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, KeyboardFragment.getInstance(),"KeyboardFragment")
                    .commit();
        }
    }

    @Override
    public void onSubmitStudentID(String studentID) {
        Intent intent = new Intent();
        intent.putExtra("studentID", studentID);
        setResult(MainActivity.REQUEST_KEYBOARD_INPUT, intent);
        finish();
    }
}
