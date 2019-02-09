package com.example.business.friedrich.kuzan.business.model.body_design;

import android.net.Uri;

import com.example.business.friedrich.kuzan.business.model.enumeration.TypeBody;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class ImageText extends BodyDesign {

    private Uri mUri;
    private String mText;
    private String mSUri;

    public ImageText() {
    }

    public ImageText(TypeBody mTypeBody) {
        super(mTypeBody);
    }

    public ImageText(TypeBody mTypeBody, Uri mUri, String mText, String mSUri) {
        super(mTypeBody);
        this.mUri = mUri;
        this.mText = mText;
        this.mSUri = mSUri;
    }

    public ImageText clone() {
        return new ImageText(mTypeBody, mUri, mText, mSUri);
    }

    @Exclude
    @Override
    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }

    @Exclude
    @Override
    public Uri getmUri() {
        return mUri;
    }

    @Override
    public void setmText(String mText) {
        this.mText = mText;
    }

    @Override
    public String getmText() {
        return mText;
    }

    @Override
    public void setmSUri(String mSUri) {
        this.mSUri = mSUri;
    }

    @Override
    public String getmSUri() {
        return mSUri;
    }


    @Exclude
    @Override
    public void setmUris(ArrayList<Uri> mUris) {

    }

    @Exclude
    @Override
    public void setmSUris(ArrayList<String> mSUris) {

    }

    @Exclude
    @Override
    public ArrayList<Uri> getmUris() {
        return null;
    }

    @Exclude
    @Override
    public ArrayList<String> getmSUris() {
        return null;
    }
}
