package com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus;

public class EBLetterSize {
    private String mName;
    private int mSize;

    public EBLetterSize(String mName, int mSize) {
        this.mName = mName;
        this.mSize = mSize;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmSize() {
        return mSize;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }
}
