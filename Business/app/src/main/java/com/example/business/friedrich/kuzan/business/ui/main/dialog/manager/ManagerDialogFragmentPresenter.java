package com.example.business.friedrich.kuzan.business.ui.main.dialog.manager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.Manager;
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
public class ManagerDialogFragmentPresenter extends MvpPresenter<IManagerDialogFragmentView> {

    @Inject
    @Singleton
    FirebaseAuth mAuth;

    @Inject
    @Singleton
    DatabaseReference mDRef;

    public ManagerDialogFragmentPresenter() {
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
                                        mDRef.child("Managers")
                                                .child(mAuth.getUid())
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        Manager manager = dataSnapshot.getValue(Manager.class);
                                                        if (manager != null) {
                                                            getViewState().saveType(1);
                                                        } else {
                                                            getViewState().showMessage(message);
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                    });
                        } else {
                            getViewState().showMessage(message);
                        }
                    });
        }
    }

    void signOut(GoogleApiClient client) {
        Auth.GoogleSignInApi.signOut(client);
    }
}
