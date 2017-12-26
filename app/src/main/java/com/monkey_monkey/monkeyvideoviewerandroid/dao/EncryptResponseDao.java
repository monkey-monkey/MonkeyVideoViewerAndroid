package com.monkey_monkey.monkeyvideoviewerandroid.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 24/12/2017 AD.
 */

public class EncryptResponseDao {
    @SerializedName("path")         private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
