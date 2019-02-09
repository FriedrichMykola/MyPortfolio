package com.example.business.friedrich.kuzan.business.model.body_design;

import android.net.Uri;

import com.example.business.friedrich.kuzan.business.model.enumeration.TypeBody;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class OnlyText extends BodyDesign {

    private String mText;

    public OnlyText() {
    }

    public OnlyText(TypeBody mTypeBody) {
        super(mTypeBody);
    }

    public OnlyText(TypeBody mTypeBody, String mText) {
        super(mTypeBody);
        this.mText = mText;
    }

    @Override
    public void setmText(String mText) {
        this.mText = mText;
    }

    @Override
    public String getmText() {
        return mText;
    }

    public OnlyText clone() {
        return new OnlyText(mTypeBody, mText);
    }

    @Exclude
    @Override
    public void setmUri(Uri mUri) {

    }

    @Exclude
    @Override
    public void setmSUri(String mSUri) {

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
    public Uri getmUri() {
        return null;
    }

    @Exclude
    @Override
    public String getmSUri() {
        return null;
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