package com.monkey_monkey.monkeyvideoviewerandroid.manager;

import android.util.Log;

import com.monkey_monkey.monkeyvideoviewerandroid.dao.VideoListResponseDao;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 24/12/2017 AD.
 */

public class ListStudentVideoManager {
    private static final String TAG = "ListVideoManager";
    private static ListStudentVideoManager instance;
    private VideoListResponseDao videoListResponseDao;

    public interface onLoad {
        void onLoadComplete();
    }

    public static ListStudentVideoManager getInstance() {
        if (instance == null) {
            instance = new ListStudentVideoManager();
        }
        return instance;
    }

    public void getVideoList(String studentCode, final ListStudentVideoManager.onLoad callback) {
        HttpManager.getInstance().getApiService().getVideoList(studentCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoListResponseDao>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onNext(VideoListResponseDao value) {
                        Log.i(TAG, "onNext: called");
                        videoListResponseDao = value;
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

    public int getSize() {
        if (videoListResponseDao == null) {
            return 0;
        }
        if (videoListResponseDao.getFiles() == null) {
            return 0;
        }
        return videoListResponseDao.getFiles().length;
    }

    public String getVideoNameAtIndex(int index) {
        return videoListResponseDao.getFiles()[index];
    }
}
