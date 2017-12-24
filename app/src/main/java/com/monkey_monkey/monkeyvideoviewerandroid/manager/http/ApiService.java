package com.monkey_monkey.monkeyvideoviewerandroid.manager.http;

import com.monkey_monkey.monkeyvideoviewerandroid.dao.VideoListResponseDao;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by admin on 20/12/2017 AD.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("/post/v1/getStudentVdoList")
    Observable<VideoListResponseDao> getVideoList(@Field("studentCode") String studentCode);

}
