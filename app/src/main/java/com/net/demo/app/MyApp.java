package com.net.demo.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.net.demo.app.utils.ImageLoaderManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

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


public class MyApp extends Application{
    private static MyApp mInstance = null;
    public ImageLoaderManager imageLoaderManager;

//    正如名字所说，SmartRefreshLayout是一个“聪明”或者“智能”的下拉刷新布局，由于它的“智能”，它不只是支持所有的View，还支持多层嵌套的视图结构。它继承自ViewGroup 而不是FrameLayout或LinearLayout，提高了性能。 也吸取了现在流行的各种刷新布局的优点，包括谷歌官方的 SwipeRefreshLayout，其他第三方的 Ultra-Pull-To-Refresh、TwinklingRefreshLayout 。还集成了各种炫酷的 Header 和 Footer。
//    SmartRefreshLayout的目标是打造一个强大，稳定，成熟的下拉刷新框架，并集成各种的炫酷、多样、实用、美观的Header和Footer。
//
//    特点功能：
//    支持多点触摸
//    支持嵌套多层的视图结构 Layout (LinearLayout, FrameLayout...)
//    支持所有的 View（AbsListView、RecyclerView、WebView....View）
//    支持自定义并且已经集成了很多炫酷的 Header 和 Footer.
//    支持和ListView的无缝同步滚动 和 CoordinatorLayout 的嵌套滚动 .
//    支持自动刷新、自动上拉加载（自动检测列表惯性滚动到底部，而不用手动上拉）.
//    支持自定义回弹动画的插值器，实现各种炫酷的动画效果.
//            支持设置主题来适配任何场景的App，不会出现炫酷但很尴尬的情况.
//            支持设多种滑动方式：平移、拉伸、背后固定、顶层固定、全屏
//            支持所有可滚动视图的越界回弹
//    Demo
//    下载地址https://github.com/scwang90/SmartRefreshLayout/blob/master/art/app-debug.apk
    //使用指定的 Header 和 Footer  方法一 全局设置
//    注意：方法一 设置的Header和Footer的优先级是最低的，如果同时还使用了方法二、三，将会被其它方法取代
    //static 代码段可以防止内存泄露
     static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

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
