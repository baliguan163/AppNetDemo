package com.net.demo.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.net.demo.app.utils.ImageLoaderManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by Administrator on 2018/9/6.
 */
//
//下面有人问到如何加载本地图片什么的，之后把那位亲的回答补充上来，谢谢各位大大提出的不足（那时候用的时候只想到用网络图片了，
// 所以也没考虑这么多）。
//
// String imageUri = "http://site.com/image.png"; // from Web
// String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
// String imageUri = "content://media/external/audio/albumart/13"; // from content provider
// String imageUri = "assets://image.png"; // from assets
// String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
//

////图片来源于Content provider
//		String contentprividerUrl = "content://media/external/audio/albumart/13";
//                //图片来源于assets
//                String assetsUrl = Scheme.ASSETS.wrap("image.png");
//
//                //图片来源于
//                String drawableUrl = Scheme.DRAWABLE.wrap("R.drawable.image");


public class MyApp extends Application {
    private static MyApp mInstance = null;
    public ImageLoaderManager imageLoaderManager;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        imageLoaderManager = ImageLoaderManager.getInstance(this);
    }

    public static MyApp getInstance() {
        return mInstance;
    }

}
