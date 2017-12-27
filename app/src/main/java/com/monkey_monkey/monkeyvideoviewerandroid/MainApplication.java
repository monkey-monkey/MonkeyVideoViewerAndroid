package com.monkey_monkey.monkeyvideoviewerandroid;

import android.app.Application;

import com.monkey_monkey.monkeyvideoviewerandroid.util.Contextor;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by admin on 20/12/2017 AD.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Contextor.getInstance().init(getApplicationContext());
    }
}
