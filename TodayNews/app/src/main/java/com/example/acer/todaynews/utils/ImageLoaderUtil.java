package com.example.acer.todaynews.utils;

import com.example.acer.todaynews.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by acer on 2017/3/27.
 */
public class ImageLoaderUtil {
    public static DisplayImageOptions getOptions(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .build();
        return options;
    }
}
