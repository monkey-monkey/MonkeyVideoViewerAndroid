package com.monkey_monkey.monkeyvideoviewerandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monkey_monkey.monkeyvideoviewerandroid.R;

/**
 * Created by admin on 22/12/2017 AD.
 */

public class CountDownFragment extends Fragment {
    private static final String TAG = "CountDownFragment";
    private static CountDownFragment instance;

    public static CountDownFragment getInstance() {
        Log.i(TAG, "getInstance: called");
        if (instance == null) {
            instance = new CountDownFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_count_down, container, false);
        initInstance(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {

    }
}
