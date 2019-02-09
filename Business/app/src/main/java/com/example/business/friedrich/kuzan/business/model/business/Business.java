package com.example.business.friedrich.kuzan.business.model.business;

public class Business {

    private String mName;
    private String mCategory;
    private Design mDesign;

    public Business() {
    }

    public Business(Design mDesign) {
        this.mDesign = mDesign;
    }

    public Business(String mName, String mCategory, Design mDesign) {
        this.mName = mName;
        this.mCategory = mCategory;
        this.mDesign = mDesign;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public Design getmDesign() {
        return mDesign;
    }

    public void setmDesign(Design mDesign) {
        this.mDesign = mDesign;
    }

    public Business clone() {
        Business business = new Business(new Design());

        business.setmName(this.mName);
        business.setmCategory(this.mCategory);

        business.getmDesign().setmColorBackground(mDesign.getmColorBackground());
        business.getmDesign().setmColorBorder(mDesign.getmColorBorder());
        business.getmDesign().setmColorBanner(mDesign.getmColorBanner());
        business.getmDesign().setmColorText(mDesign.getmColorText());

        business.getmDesign().setmSizeText(mDesign.getmSizeText());
        business.getmDesign().setmPosition(mDesign.getmPosition());
        business.getmDesign().setmCounterImages(mDesign.getmCounterImages());
        business.getmDesign().setmCounterSaved(mDesign.getmCounterSaved());

        business.getmDesign().setmLogotype(mDesign.getmLogotype());
        business.getmDesign().setmBanner(mDesign.getmBanner());

        business.getmDesign().setmBodyDesign(mDesign.cloneListBodyDesign());

        business.getmDesign().setmUriBanner(mDesign.getmUriBanner());
        business.getmDesign().setmUriLogotype(mDesign.getmUriLogotype());

        return business;
    }

    public void cloneOtherBusiness(Business business) {
        this.mName = business.mName;
        this.mCategory = business.mCategory;

        mDesign.setmColorBackground(business.mDesign.getmColorBackground());
        mDesign.setmColorBorder(business.mDesign.getmColorBorder());
        mDesign.setmColorBanner(business.mDesign.getmColorBanner());
        mDesign.setmColorText(business.mDesign.getmColorText());

        mDesign.setmSizeText(business.mDesign.getmSizeText());
        mDesign.setmPosition(business.mDesign.getmPosition());
        mDesign.setmCounterImages(business.mDesign.getmCounterImages());
        mDesign.setmCounterSaved(business.mDesign.getmCounterSaved());

        mDesign.setmLogotype(business.mDesign.getmLogotype());
        mDesign.setmBanner(business.mDesign.getmBanner());

        mDesign.setmBodyDesign(business.mDesign.cloneListBodyDesign());

        mDesign.setmUriBanner(business.mDesign.getmUriBanner());
        mDesign.setmUriLogotype(business.mDesign.getmUriLogotype());
    }
}