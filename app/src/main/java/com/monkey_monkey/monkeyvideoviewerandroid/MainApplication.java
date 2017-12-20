package com.monkey_monkey.monkeyvideoviewerandroid;

import android.app.Application;

import com.monkey_monkey.monkeyvideoviewerandroid.util.Contextor;

/**
 * Created by admin on 20/12/2017 AD.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }
}
