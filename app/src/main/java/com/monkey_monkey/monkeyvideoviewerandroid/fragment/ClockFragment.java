package com.monkey_monkey.monkeyvideoviewerandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.adapter.ClockFragmentViewPagerAdapter;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_clock, container, false);
        initInstance(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {
        ViewPager viewPager = rootView.findViewById(R.id.view_pager);
        viewPager.setAdapter(ClockFragmentViewPagerAdapter.getInstance(new ClockFragmentViewPagerAdapter.onInit() {
            @Override
            public FragmentManager getFragmentManager() {
                return getChildFragmentManager();
            }
        }));
    }
}
