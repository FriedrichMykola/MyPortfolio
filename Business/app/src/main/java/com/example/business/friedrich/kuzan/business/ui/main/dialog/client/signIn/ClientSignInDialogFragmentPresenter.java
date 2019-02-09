package com.example.business.friedrich.kuzan.business.ui.main.dialog.client.signIn;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.Client;
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
public class ClientSignInDialogFragmentPresenter extends MvpPresenter<IClientSignInDialogFragmentView> {

    @Inject
    @Singleton
    FirebaseAuth mAuth;

    @Inject
    @Singleton
    DatabaseReference mDRef;

    @Inject
    @Singleton
    Client mClient;

    public ClientSignInDialogFragmentPresenter() {
        MyApp.mComponent.Inject(this);
    }

    void handleResult(GoogleSignInResult result, String message) {
        /*code is hidden*/
    }

    void signOut(GoogleApiClient client) {
        Auth.GoogleSignInApi.signOut(client);
    }
}
