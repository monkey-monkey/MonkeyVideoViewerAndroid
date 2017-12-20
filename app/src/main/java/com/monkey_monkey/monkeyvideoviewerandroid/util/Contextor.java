package com.monkey_monkey.monkeyvideoviewerandroid.util;

import android.content.Context;

/**
 * Created by admin on 20/12/2017 AD.
 */

public class Contextor {
    private static Contextor instance;
    private Context mContext;

    public static Contextor getInstance() {
        if (instance == null) {
            instance = new Contextor();
        }
        return instance;
    }

    public void init(Context mContext) {
        this.mContext = mContext;
    }

    public Context getmContext() {
        return mContext;
    }
}
