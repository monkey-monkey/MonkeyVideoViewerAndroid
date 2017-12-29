package com.monkey_monkey.monkeyvideoviewerandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin on 23/12/2017 AD.
 */

public class StopWatchService extends Service {

    private static final String TAG = "StopWatchService";
    private static boolean pause = false;
    private static StopWatchService.countDownListener callback;
    private static int second = 0;
    private static boolean requestStop = false;

    public interface countDownListener {
        void onTimeChange(int second);

        void onStopWatchStop();
    }

    public static void init(int second, StopWatchService.countDownListener callback) {
        StopWatchService.second = second;
        StopWatchService.callback = callback;
        Log.i(TAG, "init: called");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            while (Integer.MAX_VALUE > second) {
                while (!pause) {
                    second++;
                    callback.onTimeChange(second);
                    Log.d(TAG, "run: second count " + second);
                    if (second == Integer.MAX_VALUE || requestStop) {
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
            callback.onStopWatchStop();
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
