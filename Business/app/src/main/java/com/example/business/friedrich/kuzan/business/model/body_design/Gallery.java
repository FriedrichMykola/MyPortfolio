package com.example.business.friedrich.kuzan.business.model.body_design;

import android.net.Uri;

import com.example.business.friedrich.kuzan.business.model.enumeration.TypeBody;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Gallery extends BodyDesign {

    private ArrayList<Uri> mUris;
    private ArrayList<String> mSUris;

    public Gallery() {
    }

    public Gallery(TypeBody mTypeBody) {
        super(mTypeBody);

        mUris = new ArrayList<>();
        mSUris = new ArrayList<>();
    }

    public Gallery(TypeBody mTypeBody, ArrayList<Uri> mUris, ArrayList<String> mSUris) {
        super(mTypeBody);
        this.mUris = mUris;
        this.mSUris = mSUris;
    }

    public Gallery clone() {
        return new Gallery(mTypeBody, (ArrayList<Uri>) mUris.clone(), (ArrayList<String>) mSUris.clone());
    }

    @Override
    public void setmSUris(ArrayList<String> mSUris) {
        this.mSUris = mSUris;
    }

    @Override
    public ArrayList<String> getmSUris() {
        return mSUris;
    }

    @Exclude
    @Override
    public void setmUris(ArrayList<Uri> mUris) {
        this.mUris = mUris;
    }

    @Exclude
    @Override
    public ArrayList<Uri> getmUris() {
        return mUris;
    }

    @Exclude
    @Override
    public void setmUri(Uri mUri) {
    }

    @Exclude
    @Override
    public void setmText(String mText) {
    }

    @Exclude
    @Override
    public void setmSUri(String mSUri) {
    }

    @Exclude
    @Override
    public Uri getmUri() {
        return null;
    }

    @Exclude
    @Override
    public String getmText() {
        return null;
    }

    @Exclude
    @Override
    public String getmSUri() {
        return null;
    }
}