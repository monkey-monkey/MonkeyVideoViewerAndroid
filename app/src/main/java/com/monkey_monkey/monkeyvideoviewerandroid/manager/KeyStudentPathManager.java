package com.monkey_monkey.monkeyvideoviewerandroid.manager;

import android.util.Log;

import com.monkey_monkey.monkeyvideoviewerandroid.dao.KeyStudentPathResponseDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 26/12/2017 AD.
 */

public class KeyStudentPathManager {
    private static final String TAG = "KeyPathManager";
    private static KeyStudentPathManager instance;
    private KeyStudentPathManager.onLoad callback;

    public interface onLoad {
        void onGetPath(String path);

        void onLoadComplete();
    }

    public static KeyStudentPathManager getInstance() {
        if (instance == null) {
            instance = new KeyStudentPathManager();
        }
        return instance;
    }

    public void init(KeyStudentPathManager.onLoad callback) {
        this.callback = callback;
    }

    public void getKeyStudentPath(String path) {
        HttpManager.getInstance().getApiService().getKeyStudentPath(path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeyStudentPathResponseDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(KeyStudentPathResponseDao value) {
                        Log.i(TAG, "onNext: called");
                        callback.onGetPath(value.getPath());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: called");
                        callback.onLoadComplete();
                    }
                });
    }

}
