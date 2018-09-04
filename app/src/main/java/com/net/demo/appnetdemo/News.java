package com.net.demo.appnetdemo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/4.
 */

public class News implements Serializable {

    private  String title;
    private  String pubDate;
    private  int   img;
    private  int   img1;
    private  int   img2;
    private  int   img3;

    public News(String title, String pubDate, int img, int img1, int img2, int img3) {
        this.title = title;
        this.pubDate = pubDate;
        this.img = img;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

    public int getImg3() {
        return img3;
    }

    public void setImg3(int img3) {
        this.img3 = img3;
    }
}
