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

import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.service.StopWatchService;

import at.markushi.ui.CircleButton;

/**
 * Created by admin on 22/12/2017 AD.
 */

public class StopwatchFragment extends Fragment implements View.OnClickListener, StopWatchService.countDownListener {
    private static final String TAG = "StopwatchFragment";
    private static StopwatchFragment instance;
    private TextView textViewTime;
    private CircleButton btnStart;
    private int second = 0;
    private boolean running = false;

    public static StopwatchFragment getInstance() {
        if (instance == null) {
            instance = new StopwatchFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stopwatch, container, false);
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
        switch (view.getId()) {
            case R.id.btn_start:
                Log.d(TAG, "onClick: isClockRunning " + running);
                if (running) {
                    StopWatchService.pause();
                    if (StopWatchService.isPause()) {
                        btnStart.setColor(getResources().getColor(R.color.btn_green));
                        btnStart.setImageResource(R.drawable.ic_check);
                    } else {
                        btnStart.setColor(getResources().getColor(R.color.btn_yellow));
                        btnStart.setImageResource(R.drawable.ic_pause);
                    }
                } else {
                    running = true;
                    StopWatchService.init(0, this);
                    if (getActivity() != null) {
                        getActivity().startService(new Intent(getActivity(), StopWatchService.class));
                    }
                    btnStart.setColor(getResources().getColor(R.color.btn_yellow));
                    btnStart.setImageResource(R.drawable.ic_pause);
                }
                break;
            case R.id.btn_stop:
                StopWatchService.requestStopService();
                break;
            default:
                break;
        }
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
    public void onTimeChange(int second) {
        this.second = second;
        if (getActivity() != null) {
            getActivity().runOnUiThread(this::updateTime);
        }
    }

    @Override
    public void onStopWatchStop() {
        running = false;
        if (getActivity() != null) {
            getActivity().runOnUiThread(() -> {
                btnStart.setColor(getResources().getColor(R.color.btn_green));
                btnStart.setImageResource(R.drawable.ic_check);
                second = 0;
                updateTime();
            });
        }
    }
}
