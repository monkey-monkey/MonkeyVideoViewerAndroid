package com.monkey_monkey.monkeyvideoviewerandroid.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.monkey_monkey.monkeyvideoviewerandroid.R;

public class KeyboardActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "KeyboardActivity";
    private TextView textView;
    private String currentText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {
        textView = findViewById(R.id.text_view);

        Button btn0 = findViewById(R.id.btn_0);
        btn0.setOnClickListener(this);

        Button btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);

        Button btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);

        Button btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);

        Button btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);

        Button btn5 = findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);

        Button btn6 = findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);

        Button btn7 = findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);

        Button btn8 = findViewById(R.id.btn_8);
        btn8.setOnClickListener(this);

        Button btn9 = findViewById(R.id.btn_9);
        btn9.setOnClickListener(this);

        ImageButton btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);

        ImageButton btnEnter = findViewById(R.id.btn_enter);
        btnEnter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: called");
        switch (view.getId()) {
            case R.id.btn_delete:
                deleteText();
                break;
            case R.id.btn_enter:
                if (currentText.length() == 6) {
                    Intent intent = new Intent();
                    intent.putExtra("studentID", textView.getText().toString());
                    setResult(MainActivity.REQUEST_KEYBOARD_INPUT, intent);
                    finish();
                } else {
                    showAlertDialog();
                }
                break;
            case R.id.btn_0:
                appendText("0");
                break;
            case R.id.btn_1:
                appendText("1");
                break;
            case R.id.btn_2:
                appendText("2");
                break;
            case R.id.btn_3:
                appendText("3");
                break;
            case R.id.btn_4:
                appendText("4");
                break;
            case R.id.btn_5:
                appendText("5");
                break;
            case R.id.btn_6:
                appendText("6");
                break;
            case R.id.btn_7:
                appendText("7");
                break;
            case R.id.btn_8:
                appendText("8");
                break;
            case R.id.btn_9:
                appendText("9");
                break;
            default:
                break;
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(KeyboardActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(KeyboardActivity.this);
        }
        builder.setTitle("Wrong StudentID!")
                .setMessage("StudentID should have 6-digit")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private void appendText(String value) {
        if (textView.getText().toString().length() < 6
                || textView.getText().toString().equals(getResources().getString(R.string.enter_student_id))) {
            if (textView.getText().toString().equals(getResources().getString(R.string.enter_student_id))) {
                currentText = value;
                textView.setText(currentText);
            } else {
                currentText = textView.getText().toString() + value;
                textView.setText(currentText);
            }
        }
    }

    private void deleteText() {
        if (textView.getText().toString().length() > 0
                && !textView.getText().toString().equals(getResources().getString(R.string.enter_student_id))) {
            currentText = currentText.substring(0, currentText.length() - 1);
            if (currentText.length() == 0) {
                textView.setText(R.string.enter_student_id);
            } else {
                textView.setText(currentText);
            }
        }
    }
}
