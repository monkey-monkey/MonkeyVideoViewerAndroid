package com.monkey_monkey.monkeyvideoviewerandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.monkey_monkey.monkeyvideoviewerandroid.fragment.CountDownFragment;
import com.monkey_monkey.monkeyvideoviewerandroid.fragment.StopwatchFragment;

/**
 * Created by admin on 22/12/2017 AD.
 */

public class ClockFragmentViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "ClockViewPagerAdapter";
    private static final int NUMBER_OF_PAGE = 2;

    public interface onInit {
        FragmentManager getFragmentManager();
    }

    private ClockFragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public static ClockFragmentViewPagerAdapter newInstance(onInit init) {
        return new ClockFragmentViewPagerAdapter(init.getFragmentManager());
    }

    @Override
    public Fragment getItem(int position) {
        Log.i(TAG, "getItem: ");
        switch (position) {
            case 0:
                return CountDownFragment.getInstance();
            case 1:
                return StopwatchFragment.getInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "CountDown";
            case 1:
                return "Stopwatch";
            default:
                return super.getPageTitle(position);
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGE;
    }
}
