package com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus;

import java.util.ArrayList;

public class EBListDeleteImage {
    private ArrayList<String> mImages;

    public EBListDeleteImage() {
    }

    public EBListDeleteImage(ArrayList<String> mImages) {
        this.mImages = mImages;
    }

    public ArrayList<String> getmImages() {
        return mImages;
    }

    public void setmImages(ArrayList<String> mImages) {
        this.mImages = mImages;
    }
}
