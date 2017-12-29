package com.monkey_monkey.monkeyvideoviewerandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.service.CountDownService;

import at.markushi.ui.CircleButton;

/**
 * Created by admin on 22/12/2017 AD.
 */

public class CountDownFragment extends Fragment implements View.OnClickListener, RadialTimePickerDialogFragment.OnTimeSetListener, CountDownService.countDownListener {
    private static final String TAG = "CountDownFragment";
    private static CountDownFragment instance;
    private TextView textViewTime;
    private CircleButton btnStart;
    private int second = 0;
    private boolean running = false;

    public static CountDownFragment getInstance() {
        Log.i(TAG, "newInstance: called");
        if (instance == null) {
            instance = new CountDownFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: called");
        View rootView = inflater.inflate(R.layout.fragment_count_down, container, false);
        initInstance(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {
        textViewTime = rootView.findViewById(R.id.text_view_time);
        textViewTime.setOnClickListener(this);

        btnStart = rootView.findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);

        CircleButton btnStop = rootView.findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick: called");
        switch (view.getId()) {
            case R.id.text_view_time:
                RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                        .setOnTimeSetListener(this)
                        .setStartTime(0, 0)
                        .setDoneText("OK")
                        .setCancelText("Cancel");
                rtpd.show(getChildFragmentManager(), "TimePicker");
                break;
            case R.id.btn_stop:
                CountDownService.requestStopService();
                break;
            case R.id.btn_start:
                if (running) {
                    CountDownService.pause();
                    if (CountDownService.isPause()) {
                        btnStart.setImageResource(R.drawable.ic_check);
                        btnStart.setColor(getResources().getColor(R.color.btn_green));
                    } else {
                        btnStart.setImageResource(R.drawable.ic_pause);
                        btnStart.setColor(getResources().getColor(R.color.btn_yellow));
                    }
                } else {
                    running = true;
                    CountDownService.init(second, this);
                    if (getActivity() != null) {
                        getActivity().startService(new Intent(getActivity(), CountDownService.class));
                    }
                    btnStart.setColor(getResources().getColor(R.color.btn_yellow));
                    btnStart.setImageResource(R.drawable.ic_pause);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
        second = hourOfDay * 3600 + minute * 60;
        updateTime();
    }

    public void updateSecond(int second) {
        this.second = second;
        updateTime();
    }

    private void updateTime() {
        String hourLabel = "" + (second / 3600);
        String minuteLabel = "" + ((second % 3600) / 60);
        String secondLabel = "" + (second % 60);
        if (hourLabel.length() == 1) {
            hourLabel = "0" + hourLabel;
        }
        if (minuteLabel.length() == 1) {
            minuteLabel = "0" + minuteLabel;
        }
        if (secondLabel.length() == 1) {
            secondLabel = "0" + secondLabel;
        }
        Log.d(TAG, "updateTime: " + hourLabel + " : " + minuteLabel + " : " + secondLabel);
        textViewTime.setText(hourLabel + " : " + minuteLabel + " : " + secondLabel);
    }

    @Override
    public void onTimeChange(final int second) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(() -> updateSecond(second));
        }
    }

    @Override
    public void onCountDownEnd() {
        Log.i(TAG, "onStopWatchStop: called");
        running = false;
        if (getActivity() != null) {
            getActivity().runOnUiThread(() -> {
                updateSecond(0);
                btnStart.setColor(getResources().getColor(R.color.btn_green));
                btnStart.setImageResource(R.drawable.ic_check);
            });
            getActivity().stopService(new Intent(getActivity(), CountDownService.class));
        }
    }
}
