package com.example.business.friedrich.kuzan.business.ui.business;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.IApplyNewDesign;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;
import javax.inject.Singleton;

@InjectViewState
public class BusinessActivityPresenter extends MvpPresenter<IBusinessActivityView> implements IApplyNewDesign {

    @Inject
    @Singleton
    Business mBusiness;

    @Inject
    @Singleton
    FirebaseAuth mAuth;

    @Inject
    @Singleton
    DatabaseReference mDRef;

    private boolean mChangeDesign;
    private boolean mDesign;
    private boolean mFlag;

    private Business mCloneBusiness;

    public BusinessActivityPresenter() {
        MyApp.mComponent.Inject(this);

        mFlag = true;

        if (mBusiness.getmDesign().getmLogotype() == null) {
            mDesign = false;
        } else {
            mDesign = true;
        }
    }

    public void changeFlag() {
        if (!mChangeDesign) {
            restoreDesign();
        } else {
            mCloneBusiness = null;
        }

        mFlag = !mFlag;
        if (mFlag) {
            getViewState().changePositionButtonDesign(0);
        } else {
            mCloneBusiness = mBusiness.clone();
            getViewState().changePositionButtonDesign(1);
        }

    }

    public void setmChangeDesign(boolean changeDesign) {
        this.mChangeDesign = changeDesign;
    }

    private void restoreDesign() {
        /*code is hidden*/
    }

    public boolean isDesign() {
        return mDesign;
    }

    @Override
    public void applyDesign() {
        mChangeDesign = true;
        mDesign = true;
        changeFlag();
    }
}
