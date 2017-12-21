package com.monkey_monkey.monkeyvideoviewerandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.monkey_monkey.monkeyvideoviewerandroid.R;

/**
 * Created by admin on 21/12/2017 AD.
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MainFragment";
    private static MainFragment instance;
    private MainFragment.onChangePageListener callback;

    public interface onChangePageListener {
        void onCallBarcodeScanner();

        void onCallKeyboardInput();

        void onCallClock();

        void onCallScanner();
    }

    public static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    public void init(MainFragment.onChangePageListener callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstance(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {
        ImageButton cameraBtn = rootView.findViewById(R.id.camera_btn);
        cameraBtn.setOnClickListener(this);

        ImageButton keyboardBtn = rootView.findViewById(R.id.keyboard_btn);
        keyboardBtn.setOnClickListener(this);

        ImageButton clockBtn = rootView.findViewById(R.id.clock_btn);
        clockBtn.setOnClickListener(this);

        ImageButton sheetBtn = rootView.findViewById(R.id.sheet_btn);
        sheetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_btn:
                callback.onCallBarcodeScanner();
                break;
            case R.id.keyboard_btn:
                callback.onCallKeyboardInput();
                break;
            case R.id.clock_btn:
                callback.onCallClock();
                break;
            case R.id.sheet_btn:
                callback.onCallScanner();
                break;
            default:
                break;
        }
    }
}
