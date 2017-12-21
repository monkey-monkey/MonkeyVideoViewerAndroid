package com.monkey_monkey.monkeyvideoviewerandroid.fragment;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.monkey_monkey.monkeyvideoviewerandroid.R;

/**
 * Created by admin on 21/12/2017 AD.
 */

public class KeyboardFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "KeyboardFragment";
    private static KeyboardFragment instance;
    private TextView textView;
    private String currentText = "";
    private KeyboardFragment.onRequestActivityChangeListener callback;

    public interface onRequestActivityChangeListener {
        void onSubmitStudentID(String studentID);
    }

    public static KeyboardFragment getInstance() {
        if (instance == null) {
            instance = new KeyboardFragment();
        }
        return instance;
    }

    public void init(KeyboardFragment.onRequestActivityChangeListener callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_keyboard, container, false);
        initInstance(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {
        textView = rootView.findViewById(R.id.text_view);

        Button btn0 = rootView.findViewById(R.id.btn_0);
        btn0.setOnClickListener(this);

        Button btn1 = rootView.findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);

        Button btn2 = rootView.findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);

        Button btn3 = rootView.findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);

        Button btn4 = rootView.findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);

        Button btn5 = rootView.findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);

        Button btn6 = rootView.findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);

        Button btn7 = rootView.findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);

        Button btn8 = rootView.findViewById(R.id.btn_8);
        btn8.setOnClickListener(this);

        Button btn9 = rootView.findViewById(R.id.btn_9);
        btn9.setOnClickListener(this);

        ImageButton btnDelete = rootView.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(this);

        ImageButton btnEnter = rootView.findViewById(R.id.btn_enter);
        btnEnter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                deleteText();
                break;
            case R.id.btn_enter:
                if (currentText.length() == 6) {
                    callback.onSubmitStudentID(textView.getText().toString());
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
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
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
