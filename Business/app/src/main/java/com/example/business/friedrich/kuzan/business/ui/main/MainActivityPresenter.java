package com.example.business.friedrich.kuzan.business.ui.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.example.business.friedrich.kuzan.business.model.Client;
import com.example.business.friedrich.kuzan.business.model.Manager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<IMainActivityView> {

    @Inject
    @Singleton
    Client mClient;

    @Inject
    @Singleton
    Business mBusiness;

    @Inject
    @Singleton
    Manager mManager;

    @Inject
    @Singleton
    FirebaseAuth mAuth;

    @Inject
    @Singleton
    DatabaseReference mDRef;

    private GoogleApiClient mGoogleApiClient;
    private byte mCode;

    MainActivityPresenter(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
        MyApp.mComponent.Inject(this);
    }

    public void setmCode(byte mCode) {
        this.mCode = mCode;
    }

    void clearUnnecessaryObjects(byte who) {
        /*code is hidden*/
    }

    void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            mAuth.signInWithCredential(GoogleAuthProvider.getCredential(account.getIdToken(), null))
                    .addOnSuccessListener(authResult -> {
                        clearUnnecessaryObjects(mCode);
                        changeType(mAuth.getUid());
                    });
        }
    }

    private void changeType(String uid) {
        switch (mCode) {
            case 0: {
                /*code is hidden*/
                break;
            }
            case 1: {

            }
            case 2: {
                /*code is hidden*/
            }
        }
    }

    GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }
}
