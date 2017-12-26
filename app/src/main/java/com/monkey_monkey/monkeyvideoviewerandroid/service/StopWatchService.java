package com.monkey_monkey.monkeyvideoviewerandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin on 23/12/2017 AD.
 */

public class CountDownService extends Service {

    private static final String TAG = "CountDownService";
    private static boolean pause = false;
    private static CountDownService.countDownListener callback;
    private static int second = 0;
    private static boolean requestStop = false;

    public interface countDownListener {
        void onTimeChange(int second);

        void onCountDownEnd();
    }

    public static void init(int second, CountDownService.countDownListener callback) {
        CountDownService.second = second;
        CountDownService.callback = callback;
        Log.i(TAG, "init: called");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (second > 0) {
                    while (!pause) {
                        second--;
                        callback.onTimeChange(second);
                        Log.d(TAG, "run: second left " + second);
                        if (second == 0 || requestStop) {
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (requestStop) {
                        second = 0;
                        pause = false;
                        requestStop = false;
                        break;
                    }
                }
                callback.onCountDownEnd();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    public static void pause() {
        pause = !pause;
    }

    public static boolean isPause() {
        return pause;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static void requestStopService() {
        requestStop = true;
    }
}
