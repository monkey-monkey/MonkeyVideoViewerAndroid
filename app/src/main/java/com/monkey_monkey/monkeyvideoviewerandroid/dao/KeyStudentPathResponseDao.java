package com.monkey_monkey.monkeyvideoviewerandroid.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 26/12/2017 AD.
 */

public class KeyStudentPathResponseDao {
    @SerializedName("path") private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
