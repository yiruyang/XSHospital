package com.example.a12878.xshospital.base;

import chuangyuan.ycj.videolibrary.listener.ItemVideo;

/**
 * Created by 12878 on 2018/8/13.
 */

public class VideoItem implements ItemVideo {

    private String uri;

    public VideoItem(String uri) {
        this.uri = uri;
    }

    @Override
    public String getVideoUri() {
        return uri;
    }
}
