package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.fragment.MainFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.onChangePageListener {

    private static final String TAG = "MainActivity";
    public static final int REQUEST_KEYBOARD_INPUT = 0;
    public static final int REQUEST_QR_CODE_SCANNER = 1;
    public static final int REQUEST_BARCODE_SCANNER = 2;

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
                if (data != null && data.getStringExtra(KeyboardActivity.ACTIVITY_RESULT) != null) {
                    startBrowseVideoActivity(data.getStringExtra(KeyboardActivity.ACTIVITY_RESULT));
                }
                break;
            case REQUEST_QR_CODE_SCANNER:
                if (data != null && data.getStringExtra(ScannerActivity.ACTIVITY_RESULT) != null) {
                    startOpenFileActivity(data.getStringExtra(ScannerActivity.ACTIVITY_RESULT));
                }
                break;
            case REQUEST_BARCODE_SCANNER:
                if (data != null && data.getStringExtra(ScannerActivity.ACTIVITY_RESULT) != null) {
                    startBrowseVideoActivity(data.getStringExtra(ScannerActivity.ACTIVITY_RESULT));
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    private void startBrowseVideoActivity(String studentID) {
        Log.i(TAG, "startBrowseVideoActivity: " + studentID);
    }

    private void startOpenFileActivity(String filePath){
        Log.i(TAG, "startOpenFileActivity: " + filePath);
    }

    @Override
    public void onCallBarcodeScanner() {
        Intent intent = new Intent(MainActivity.this, StreamVideoActivity.class);
        startActivityForResult(intent, REQUEST_BARCODE_SCANNER);
    }

    @Override
    public void onCallKeyboardInput() {
        Intent intent = new Intent(MainActivity.this, KeyboardActivity.class);
        startActivityForResult(intent, REQUEST_KEYBOARD_INPUT);
    }

    @Override
    public void onCallClock() {
        Intent intent = new Intent(MainActivity.this, ClockActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCallScanner() {
        Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
        startActivityForResult(intent, REQUEST_QR_CODE_SCANNER);
    }
}
