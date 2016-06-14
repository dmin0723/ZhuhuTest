package com.dengmin.zhuhutest;

import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.litepal.LitePalApplication;

/**
 * 配置universal-image-loader
 */
public class MyApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(this);
    }

    //初始化ImageLoader
    public static void initImageLoader(Context context) {
        //创建ImageLoader配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .denyCacheImageMultipleSizesInMemory()
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO)//default
                .build();

        ImageLoader.getInstance().init(config);
    }
}
