package com.example.business.friedrich.kuzan.business.ui.main.dialog.business.signIn;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;

@InjectViewState
public class BusinessSignInDialogFragmentPresenter extends MvpPresenter<IBusinessSignInDialogFragmentView> {

    @Inject
    @Singleton
    FirebaseAuth mAuth;

    @Inject
    @Singleton
    DatabaseReference mDRef;

    @Inject
    @Singleton
    Business mBusiness;

    public BusinessSignInDialogFragmentPresenter() {
        MyApp.mComponent.Inject(this);
    }

    void handleResult(GoogleSignInResult result, String message) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            mAuth.fetchSignInMethodsForEmail(account.getEmail())
                .addOnCompleteListener(task -> {
                    if (!task.getResult().getSignInMethods().isEmpty()) {
                        mAuth.signInWithCredential(GoogleAuthProvider
                            .getCredential(account
                                .getIdToken(), null))
                            .addOnSuccessListener(authResult -> {
                                mDRef.child("Businesses")
                                    .child(mAuth.getUid())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Business business = dataSnapshot.getValue(Business.class);
                                            if (business != null) {
                                                mBusiness.setmName(business.getmName());
                                                mBusiness.setmCategory(business.getmCategory());

                                                if (business.getmDesign() != null) {
                                                    mBusiness.getmDesign()
                                                        .setmLogotype(business
                                                            .getmDesign()
                                                            .getmLogotype());
                                                }

                                                getViewState().openBusinessActivity();
                                                getViewState().saveType(2);
                                            } else {
                                                getViewState().showMessage(message);
                                                getViewState().closeDialog();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                            });
                    } else {
                        mAuth.signInWithCredential(GoogleAuthProvider.getCredential(account.getIdToken(), null))
                            .addOnSuccessListener(authResult -> getViewState().signUpEntrepreneur());
                    }
                });
        }
    }

    void signOut(GoogleApiClient client) {
        Auth.GoogleSignInApi.signOut(client);
    }

}
