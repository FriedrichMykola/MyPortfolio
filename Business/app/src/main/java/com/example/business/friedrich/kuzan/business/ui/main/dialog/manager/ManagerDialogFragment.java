package com.example.business.friedrich.kuzan.business.ui.main.dialog.manager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatDialogFragment;
import com.example.business.friedrich.kuzan.business.ui.main.MainActivity;
import com.example.business.friedrich.kuzan.business.ui.manager.ManagerActivity;
import com.example.business.friedrich.kuzan.business.ui.сlient.ClientActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ManagerDialogFragment extends MvpAppCompatDialogFragment implements IManagerDialogFragmentView {

    private static final int REQUEST_CODE = 1796;
    private SignInButton mButtonSignIn;
    private GoogleApiClient mGoogleApiClient;

    @InjectPresenter
    ManagerDialogFragmentPresenter mPresenter;

    public ManagerDialogFragment() {
    }

    @SuppressLint("ValidFragment")
    public ManagerDialogFragment(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.signOut(mGoogleApiClient);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE: {
                mPresenter.handleResult(Auth.GoogleSignInApi.getSignInResultFromIntent(data), getString(R.string.error_manager));
                break;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_sign_in, container, false);

        mButtonSignIn = view.findViewById(R.id.button_sign_in);
        mButtonSignIn.setOnClickListener(v -> {
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(intent, REQUEST_CODE);
        });


        return view;
    }

    @Override
    public void showMessage(String ss) {
        Toast.makeText(getActivity(), ss, Toast.LENGTH_LONG).show();
    }

    @Override
    public void saveType(int type) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        pref.edit().putInt(MainActivity.KEY_PREF, type).commit();
    }

    @Override
    public void openClientActivity() {
        startActivity(new Intent(getContext(), ManagerActivity.class));
        dismiss();
    }
}
