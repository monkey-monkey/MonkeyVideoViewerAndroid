package com.monkey_monkey.monkeyvideoviewerandroid.manager;

import android.util.Log;

import com.monkey_monkey.monkeyvideoviewerandroid.dao.EncryptResponseDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 24/12/2017 AD.
 */

public class EncryptVideoPathManager {
    private static final String TAG = "EncryptVideoPathManager";
    private static EncryptVideoPathManager instance;

    public interface onLoad {
        void onPathReceive(String path);
    }

    public static EncryptVideoPathManager getInstance() {
        if (instance == null) {
            instance = new EncryptVideoPathManager();
        }
        return instance;
    }

    public void encrypt(String value, final EncryptVideoPathManager.onLoad callback) {
        HttpManager.getInstance().getApiService().encrypt(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EncryptResponseDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(EncryptResponseDao value) {
                        Log.i(TAG, "onNext: called");
                        callback.onPathReceive(value.getPath());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: called");
                    }
                });
    }
}
