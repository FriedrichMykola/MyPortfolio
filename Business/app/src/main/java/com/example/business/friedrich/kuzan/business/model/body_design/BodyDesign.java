package com.example.business.friedrich.kuzan.business.model.body_design;

import android.net.Uri;

import com.example.business.friedrich.kuzan.business.model.enumeration.TypeBody;

import java.util.ArrayList;

public abstract class BodyDesign {

    protected TypeBody mTypeBody;

    public BodyDesign() {
    }

    public BodyDesign(TypeBody mTypeBody) {
        this.mTypeBody = mTypeBody;
    }

    public void setmTypeBody(TypeBody mTypeBody) {
        this.mTypeBody = mTypeBody;
    }

    public TypeBody getmTypeBody() {
        return mTypeBody;
    }

    public abstract void setmUri(Uri mUri);

    public abstract void setmText(String mText);

    public abstract void setmSUri(String mSUri);

    public abstract void setmUris(ArrayList<Uri> mUris);

    public abstract void setmSUris(ArrayList<String> mSUris);

    public abstract Uri getmUri();

    public abstract String getmText();

    public abstract String getmSUri();

    public abstract ArrayList<Uri> getmUris();

    public abstract ArrayList<String> getmSUris();

}
