package com.net.demo.app.utils;

/**
 * Created by Administrator on 2018/9/6.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 项目名:  Component
 * 包名:    com.bighuan.bighuansdk.imageloader
 * 文件名:  ImageLoaderManager
 * 作者:    bighuan
 * 创建时间:2017/2/28 20:34
 * 描述:    UniverseImageLoader工具类,加载图片,尤其是网络图片
 */

public class ImageLoaderManager {
    private static final int THREAD_COUNT = 4;//表明我们的UIL最多可以有多少条线程
    private static final int PRIORITY = 2;    //图片加载的一个优先级
    private static final int MEMORY_CACHE_SIZE = 2 * 1024 * 1024;//内存缓存大小
    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;//表明UIL最多缓存多少图片
    private static final int CONNECTION_TIME_OUT = 5 * 1000;//连接超时时间
    private static final int READ_TIME_OUT = 30 * 1000;//读取超时时间
    private static ImageLoaderManager mInstance = null;
    private static ImageLoader mLoader = null;
    public static ImageLoaderManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ImageLoaderManager.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 私有构造方法完成初始化工作
     *
     * @param context
     */
    private ImageLoaderManager(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(THREAD_COUNT)//配置图片线程下载的最大数量
                .threadPriority(Thread.NORM_PRIORITY - PRIORITY)//设置优先级
                .denyCacheImageMultipleSizesInMemory()//防止缓存多套图片到内存中
                .memoryCache(new WeakMemoryCache())//使用弱引用内存缓存
                .diskCacheSize(DISK_CACHE_SIZE)//分配硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//使用MD5命名文件
                .tasksProcessingOrder(QueueProcessingType.LIFO)//图片下载顺序
                .defaultDisplayImageOptions(getDefaultOptions())//默认的图片加载Options
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME_OUT, READ_TIME_OUT))
                //设置图片下载器
                .writeDebugLogs()//debug环境下输出日志
                .build();
        ImageLoader.getInstance().init(configuration);
        mLoader = ImageLoader.getInstance();
    }

    /**
     * 默认的图片显示Options,可设置图片的缓存策略，编解码方式等，非常重要
     *
     * @return
     */
    private DisplayImageOptions getDefaultOptions() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                //                .showImageForEmptyUri(R.drawable...)//图片地址为空的时候显示的图片
                //                .showImageOnFail(R.drawable...)//出错的时候显示的图片
                .cacheInMemory(true)//设置图片可以缓存在内存中
                .cacheOnDisk(true)//设置图片可以缓存在硬盘中
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)//使用的图片解码类型
                .decodingOptions(new BitmapFactory.Options())//图片解码配置
                .resetViewBeforeLoading(true)
                .build();
        return options;

    }

    /**
     * 设置图片
     *
     * @param imageView
     * @param path
     * @param listener
     * @param options
     */
    public void displayImage(ImageView imageView, String path,
                             ImageLoadingListener listener, DisplayImageOptions options) {
        if (mLoader != null) {
            mLoader.displayImage(path, imageView, options, listener);
        }
    }

    public void displayImage(ImageView imageView, String path, ImageLoadingListener listener) {
        if (mLoader != null) {
            mLoader.displayImage(path, imageView, listener);
        }
    }

    public void displayImage(ImageView imageView, String path) {
        displayImage(imageView, path, null);
    }

    public Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }


}
