package com.example.acn0036.kakaotalkreceiver;

import android.graphics.Bitmap;

/**
 * Created by ACN0036 on 2017-10-17.
 */

public class NotificationData {
    Bitmap mImg;
    String mName, mText, mTime;

    public Bitmap getImage() {
        return mImg;
    }

    public void setImage(Bitmap img) {
        this.mImg = img;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        this.mTime = time;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

}
