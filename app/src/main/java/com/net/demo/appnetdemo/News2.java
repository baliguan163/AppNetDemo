package com.net.demo.appnetdemo;

import android.os.Parcel;
import android.os.Parcelable;

import android.os.Parcelable;

//Parcelable  Android 独有的
public class News2 implements Parcelable {

    private String title;
    private String pubDate;
    //    private String newId;
    private int img;
    private String from;

    public News2(String title, String pubDate, String newId, int img, String from) {
        this.title = title;
        this.pubDate = pubDate;
//        this.newId = newId;
        this.img = img;
        this.from = from;
    }

    protected News2(Parcel in) {
        title = in.readString();
        pubDate = in.readString();
        img = in.readInt();
        from = in.readString();
    }

//    public static final Creator<News2> CREATOR = new Creator<News2>() {
//        @Override
//        public News2 createFromParcel(Parcel in) {
//            return new News2(in);
//        }
//        @Override
//        public News2[] newArray(int size) {
//            return new News2[size];
//        }
//    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

//    public String getNewId() {
//        return newId;
//    }
//
//    public void setNewId(String newId) {
//        this.newId = newId;
//    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(pubDate);
        parcel.writeInt(img);
        parcel.writeString(from);
    }
}
