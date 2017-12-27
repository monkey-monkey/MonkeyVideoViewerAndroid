package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.fragment.MainFragment;
import com.monkey_monkey.monkeyvideoviewerandroid.manager.KeyStudentPathManager;

import java.io.File;

import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.remote.DownloadFileUrlConnectionImpl;

public class MainActivity extends AppCompatActivity implements MainFragment.onChangePageListener, DownloadFile.Listener, KeyStudentPathManager.onLoad {

    private static final String TAG = "MainActivity";
    public static final int REQUEST_KEYBOARD_INPUT = 0;
    public static final int REQUEST_QR_CODE_SCANNER = 1;
    public static final int REQUEST_BARCODE_SCANNER = 2;
    private DownloadFile downloadFile;

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
        KeyStudentPathManager.getInstance().init(this);
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
                    KeyStudentPathManager.getInstance().getKeyStudentPath(data.getStringExtra(ScannerActivity.ACTIVITY_RESULT));
//                    startOpenFileActivity(data.getStringExtra(ScannerActivity.ACTIVITY_RESULT));
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

    private void startBrowseVideoActivity(String studentCode) {
        Log.i(TAG, "startBrowseVideoActivity: " + studentCode);
        Intent intent = new Intent(MainActivity.this, BrowseVideoActivity.class);
        intent.putExtra("studentCode", studentCode);
        startActivity(intent);
    }

    private void startOpenFileActivity(String filePath) {
        Log.d(TAG, "startOpenFileActivity: filePath " + filePath);
        downloadFile = new DownloadFileUrlConnectionImpl(this, new Handler(), this);
        downloadFile.download(filePath, new File(getExternalFilesDir("pdf"), "key.pdf").getAbsolutePath());
    }

    private void launchOpenPDFIntent(String destinationPath) {
        File file = new File(destinationPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public void onCallBarcodeScanner() {
        Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
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

    @Override
    public void onSuccess(String url, String destinationPath) {
        launchOpenPDFIntent(destinationPath);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    public void onGetPath(String path) {
        Log.d(TAG, "onGetPath: path " + path);
        startOpenFileActivity(getResources().getString(R.string.base_url) + "get/v1/keyStudent?k="+path);
    }

    @Override
    public void onLoadComplete() {

    }
}
