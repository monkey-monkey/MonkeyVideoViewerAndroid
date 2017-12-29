package com.monkey_monkey.monkeyvideoviewerandroid.manager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.monkey_monkey.monkeyvideoviewerandroid.R;
import com.monkey_monkey.monkeyvideoviewerandroid.manager.http.ApiService;
import com.monkey_monkey.monkeyvideoviewerandroid.util.Contextor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 20/12/2017 AD.
 */

public class HttpManager {
    private static final String TAG = "HttpManager";
    private static  HttpManager instance;
    private ApiService apiService;

    private HttpManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contextor.getInstance().getContext().getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static HttpManager getInstance(){
        if (instance == null){
            instance = new HttpManager();
        }
        return instance;
    }

    ApiService getApiService(){
        return apiService;
    }
}
