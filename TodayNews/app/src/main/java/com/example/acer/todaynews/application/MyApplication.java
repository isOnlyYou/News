package com.example.acer.todaynews.application;

import android.app.Application;
import android.widget.Toast;

import com.bawei.library.app.AppApplication;
import com.bawei.library.db.SQLHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import cn.smssdk.SMSSDK;

/**
 * Created by acer on 2017/3/22.
 */
public class MyApplication extends Application {

    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    private static MyApplication mAppApplication;
    private SQLHelper sqlHelper;



    @Override
    public void onCreate() {
        super.onCreate();

        SMSSDK.initSDK(getApplicationContext(), "1c1662b74d3ba", "61f4998fffee45f7fcba96e84e9020ff");
        UMShareAPI.get(this);
        mAppApplication = this;

        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCacheExtraOptions(480,800)
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    /** 获取Application */
    public static MyApplication getApp() {
        return mAppApplication;
    }

    /** 获取数据库Helper */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mAppApplication);
        return sqlHelper;
    }

    /** 摧毁应用进程时候调用 */
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
    }

    public void clearAppCache() {
    }
}
