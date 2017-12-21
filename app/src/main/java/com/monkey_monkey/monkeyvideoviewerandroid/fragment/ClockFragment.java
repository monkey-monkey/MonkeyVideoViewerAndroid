package com.monkey_monkey.monkeyvideoviewerandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monkey_monkey.monkeyvideoviewerandroid.R;

/**
 * Created by admin on 21/12/2017 AD.
 */

public class ClockFragment extends Fragment {
    private static final String TAG = "ClockFragment";
    private static ClockFragment instance;

    public static ClockFragment getInstance() {
        if (instance == null) {
            instance = new ClockFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_clock, container, false);
        initInstance(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {

    }
}
