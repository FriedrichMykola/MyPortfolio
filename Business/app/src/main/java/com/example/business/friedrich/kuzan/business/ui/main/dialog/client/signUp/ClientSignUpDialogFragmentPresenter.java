package com.example.business.friedrich.kuzan.business.ui.main.dialog.client.signUp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.Client;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;
import javax.inject.Singleton;

@InjectViewState
public class ClientSignUpDialogFragmentPresenter extends MvpPresenter<IClientSignUpDialogFragmentView> {

    @Inject
    @Singleton
    Client mClient;

    @Inject
    @Singleton
    DatabaseReference mDRef;

    @Inject
    @Singleton
    FirebaseAuth mAuth;

    private boolean addedToDB;

    public ClientSignUpDialogFragmentPresenter() {
        addedToDB = false;
        MyApp.mComponent.Inject(this);
    }

    public void signInClient(String phone, String error) {
        if (!checkPhone(phone)) {
            getViewState().showMessage(error);
            return;
        }

        mClient.setmPhone(phone);

        mDRef.child("Clients")
                .child(mAuth.getUid())
                .setValue(mAuth);

        addedToDB = true;

        getViewState().openClientActivity();
    }

    private boolean checkPhone(String phone) {
        if ((phone.length() != 10) && (phone.length() != 12)) {
            return false;
        }

        for (char aPhone : phone.toCharArray()) {
            if ((aPhone < '0') || (aPhone > '9')) {
                return false;
            }
        }

        return true;
    }

    public void isClientInSystem() {
        if (!addedToDB) {
            mAuth.getCurrentUser().delete();
        }
    }
}