package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.fragment.MainFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.onChangePageListener {

    private static final String TAG = "MainActivity";
    public static final int REQUEST_KEYBOARD_INPUT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            MainFragment.getInstance().init(this);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.getInstance(), "MainFragment")
                    .commit();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_KEYBOARD_INPUT:
                if (data != null && data.getStringExtra("studentID") != null) {
                    startBrowseVideoActivity(data.getStringExtra("studentID"));
                }
                break;
            default:
                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (result != null) {
                    if (result.getContents() != null) {
                        startBrowseVideoActivity(result.getContents());
                    }
//                    if (result.getContents() == null) {
//                        Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//                    }
                } else {
                    super.onActivityResult(requestCode, resultCode, data);
                }
                break;
        }
    }

    private void startBrowseVideoActivity(String studentID) {

    }


    @Override
    public void onCallBarcodeScanner() {
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    public void onCallKeyboardInput() {
        Intent intent = new Intent(MainActivity.this, KeyboardActivity.class);
        startActivityForResult(intent, REQUEST_KEYBOARD_INPUT);
    }
}
