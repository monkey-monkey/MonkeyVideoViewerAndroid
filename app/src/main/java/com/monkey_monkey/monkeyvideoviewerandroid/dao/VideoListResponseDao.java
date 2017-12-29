package com.monkey_monkey.monkeyvideoviewerandroid.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 24/12/2017 AD.
 */

public class VideoListResponseDao {
    @SerializedName("files") private String files[];

    public String[] getFiles() {
        return files;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }
}
