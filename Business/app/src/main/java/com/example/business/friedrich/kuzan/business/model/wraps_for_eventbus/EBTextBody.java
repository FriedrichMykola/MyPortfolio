package com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus;

public class EBTextBody {
    private int mId;
    private String mText;

    public EBTextBody(int mId, String mText) {
        this.mText = mText;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public int getmId() {
        return mId;
    }
}
