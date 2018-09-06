package com.net.demo.app.utils;

/**
 * Created by Administrator on 2018/9/6.
 * listview的条目上的图片按照宽高比例进行缩放设置
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SubjectImageView extends ImageView {

    public SubjectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        //获取真实的图片
//        Drawable drawable = getDrawable();
//        if (drawable != null) {
//            //获取真实的宽
//            int width = drawable.getMinimumWidth();
//            //获取真实的高
//            int height = drawable.getMinimumHeight();
//            //计算宽和高的比例
//            float scale = (float) width / height;
//            //获取测量宽的规则
//            int withsize = MeasureSpec.getSize(widthMeasureSpec);
//            //按照比例计算高的测量规则
//            int heightsize = (int) (withsize / scale);
//            //设置高的测量规则 第一个值是按照比例计算的高 第二个参数是测量模式 精确
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightsize, MeasureSpec.EXACTLY);
//        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
}

