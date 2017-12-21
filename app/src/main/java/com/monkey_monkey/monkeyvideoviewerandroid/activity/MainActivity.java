package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.monkey_monkey.monkeyvideoviewerandroid.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        ImageButton cameraBtn = findViewById(R.id.camera_btn);
        cameraBtn.setOnClickListener(this);

        ImageButton keyboardBtn = findViewById(R.id.keyboard_btn);
        keyboardBtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_btn:
                new IntentIntegrator(this).initiateScan();
                break;
            case R.id.keyboard_btn:
                break;
            default:
                break;
        }
    }
}
