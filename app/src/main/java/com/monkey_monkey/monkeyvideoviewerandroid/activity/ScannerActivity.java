package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.barcode.Barcode;
import com.monkey_monkey.monkeyvideoviewerandroid.R;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class ScannerActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {
    private static final String TAG = "ScannerActivity";
    public static final String ACTIVITY_RESULT = "scannedResult";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
    }

    @Override
    public void onScanned(Barcode barcode) {
        Log.i(TAG, "onScanned: " + barcode.displayValue);
        Intent intent = new Intent();
        intent.putExtra(ACTIVITY_RESULT, barcode.displayValue);
        setResult(MainActivity.REQUEST_QRCODE_SCANNER, intent);
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {

    }
}
