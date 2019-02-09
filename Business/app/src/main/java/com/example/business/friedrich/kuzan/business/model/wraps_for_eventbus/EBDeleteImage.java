package com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus;

public class EBDeleteImage {
    String mPath;

    public EBDeleteImage() {
    }

    public EBDeleteImage(String mPath) {
        this.mPath = mPath;
    }

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }
}
