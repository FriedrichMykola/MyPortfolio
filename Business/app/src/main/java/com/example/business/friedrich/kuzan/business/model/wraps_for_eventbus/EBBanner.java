package com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus;

import android.net.Uri;

public class EBBanner {
    private Uri mUri;

    public EBBanner(Uri mUri) {
        this.mUri = mUri;
    }

    public Uri getmUri() {
        return mUri;
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }
}
