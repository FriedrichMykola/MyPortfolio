package com.example.business.friedrich.kuzan.business.ui.main.dialog.business.signUp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;
import javax.inject.Singleton;

@InjectViewState
public class BusinessSignUpDialogFragmentPresenter extends MvpPresenter<IBusinessSignUpDialogFragmentView> {

    @Inject
    @Singleton
    Business mBusiness;

    @Inject
    @Singleton
    DatabaseReference mDRef;

    @Inject
    @Singleton
    FirebaseAuth mAuth;

    private boolean mAddedToDB;

    public BusinessSignUpDialogFragmentPresenter() {
        mAddedToDB = false;
        MyApp.mComponent.Inject(this);
    }

    void signUpBusiness(String name, String category) {
        mBusiness.setmName(name);
        mBusiness.setmCategory(category);

        mDRef.child("Businesses")
                .child(mAuth.getUid()).
                setValue(mBusiness);

        mAddedToDB = true;

        getViewState().openBusinessActivity();
    }


    boolean ismAddedToDB() {
        return mAddedToDB;
    }

    void noAuth() {
        mAuth.getCurrentUser().delete();
    }
}
