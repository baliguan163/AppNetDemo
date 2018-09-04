package com.net.demo.appnetdemo;

/**
 * Created by Administrator on 2018/9/4.
 */

import android.graphics.drawable.Drawable;

        import android.graphics.drawable.Drawable;

public class NewsBean {
    public String title;
    public String des;
    public Drawable icon;
    public String news_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getNews_url() {
        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }



}

