package com.example.business.friedrich.kuzan.business.model.business;

import android.net.Uri;

import com.example.business.friedrich.kuzan.business.model.body_design.BodyDesign;
import com.example.business.friedrich.kuzan.business.model.body_design.Gallery;
import com.example.business.friedrich.kuzan.business.model.body_design.ImageText;
import com.example.business.friedrich.kuzan.business.model.body_design.OnlyText;
import com.example.business.friedrich.kuzan.business.model.enumeration.Position;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Design {
    private String mLogotype;
    private Uri mUriLogotype;

    private String mBanner;
    private Uri mUriBanner;

    private int mColorBackground;
    private int mColorText;
    private int mColorBorder;
    private int mColorBanner;

    private int mSizeText;
    private int mCounterImages;
    private int mCounterSaved;

    private Position mPosition;

    private ArrayList<BodyDesign> mBodyDesign;

    public Design() {
        mColorBackground = 16578559;
        mColorBanner = 16578559;
        mColorBorder = -16580089;
        mColorText = -987905;
        mPosition = Position.Center;
        mSizeText = 13;

        mCounterImages = 0;
        mCounterSaved = 0;

        mBodyDesign = new ArrayList<>();
    }

    public int getmCounterSaved() {
        return mCounterSaved;
    }

    public void setmCounterSaved(int mCounterSaved) {
        this.mCounterSaved = mCounterSaved;
    }

    public void incrementmCounterSaved() {
        ++mCounterSaved;
    }

    public String getmLogotype() {
        return mLogotype;
    }

    public void setmLogotype(String mLogotype) {
        this.mLogotype = mLogotype;
    }

    public String getmBanner() {
        return mBanner;
    }

    public void setmBanner(String mBanner) {
        this.mBanner = mBanner;
    }

    public int getmColorBackground() {
        return mColorBackground;
    }

    public void setmColorBackground(int mColorBackground) {
        this.mColorBackground = mColorBackground;
    }

    public int getmColorText() {
        return mColorText;
    }

    public void setmColorText(int mColorText) {
        this.mColorText = mColorText;
    }

    public int getmColorBorder() {
        return mColorBorder;
    }

    public void setmColorBorder(int mColorBorder) {
        this.mColorBorder = mColorBorder;
    }

    public int getmColorBanner() {
        return mColorBanner;
    }

    public void setmColorBanner(int mColorBanner) {
        this.mColorBanner = mColorBanner;
    }

    public Position getmPosition() {
        return mPosition;
    }

    public void setmPosition(Position mPosition) {
        this.mPosition = mPosition;
    }

    public int getmSizeText() {
        return mSizeText;
    }

    public void setmSizeText(int mSizeText) {
        this.mSizeText = mSizeText;
    }

    public int getmCounterImages() {
        return mCounterImages;
    }

    public void setmCounterImages(int mCounterImages) {
        this.mCounterImages = mCounterImages;
    }

    @Exclude
    public Uri getmUriLogotype() {
        return mUriLogotype;
    }

    @Exclude
    public void setmUriLogotype(Uri mUriLogotype) {
        this.mUriLogotype = mUriLogotype;
    }

    @Exclude
    public Uri getmUriBanner() {
        return mUriBanner;
    }

    @Exclude
    public void setmUriBanner(Uri mUriBanner) {
        this.mUriBanner = mUriBanner;
    }

    @Exclude
    public ArrayList<BodyDesign> getmBodyDesign() {
        return mBodyDesign;
    }

    @Exclude
    public ArrayList<BodyDesign> cloneListBodyDesign() {
        ArrayList<BodyDesign> cloneBody = new ArrayList<>();
        for (BodyDesign bodyDesign : mBodyDesign) {
            switch (bodyDesign.getmTypeBody()) {
                case BOnlyText: {
                    OnlyText onlyText = (OnlyText) bodyDesign;
                    cloneBody.add(onlyText.clone());
                    break;
                }
                case BImageTextRight:
                case BImageTextLeft: {
                    ImageText imageText = (ImageText) bodyDesign;
                    cloneBody.add(imageText.clone());
                    break;
                }
                case BGallery: {
                    Gallery gallery = (Gallery) bodyDesign;
                    cloneBody.add(gallery.clone());
                    break;
                }
            }
        }
        return cloneBody;
    }

    @Exclude
    public void setmBodyDesign(ArrayList<BodyDesign> mBodyDesign) {
        this.mBodyDesign = mBodyDesign;
    }
}
