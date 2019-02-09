package com.example.business.friedrich.kuzan.business.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatActivity;
import com.example.business.friedrich.kuzan.business.ui.business.BusinessActivity;
import com.example.business.friedrich.kuzan.business.ui.main.dialog.business.signIn.BusinessSignInSignInDialogFragment;
import com.example.business.friedrich.kuzan.business.ui.main.dialog.client.signIn.ClientSignInDialogFragment;
import com.example.business.friedrich.kuzan.business.ui.main.dialog.help.MainHelpDialogFragment;
import com.example.business.friedrich.kuzan.business.ui.main.dialog.manager.ManagerDialogFragment;
import com.example.business.friedrich.kuzan.business.ui.manager.ManagerActivity;
import com.example.business.friedrich.kuzan.business.ui.сlient.ClientActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends MvpAppCompatActivity
        implements IMainActivityView {

    private static final int REQUEST_CODE = 1800;
    public static final String KEY_PREF = "Type";

    private final static int REQUEST_READ_STORAGE = 33;

    private MaterialButton mButtonClient,
            mButtonManager,
            mButtonEntrepreneur,
            mButtonHelpClient,
            mButtonHelpManager,
            mButtonHelpBusiness;

    @InjectPresenter
    MainActivityPresenter mPresenter;

    @ProvidePresenter
    public MainActivityPresenter getMainActivityPresenter() {
        GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        GoogleApiClient client = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this, null)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build();

        return new MainActivityPresenter(client);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPermission();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int i = pref.getInt(KEY_PREF, -1);
        if (i != -1) {
            mPresenter.setmCode((byte) i);
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(mPresenter.getmGoogleApiClient());
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            mButtonClient = findViewById(R.id.button_client);
            mButtonClient.setOnClickListener(v -> {
                ClientSignInDialogFragment dialogFragment = new ClientSignInDialogFragment(mPresenter.getmGoogleApiClient());
                dialogFragment.show(getSupportFragmentManager(), null);
                mPresenter.clearUnnecessaryObjects((byte) 0);
            });

            mButtonHelpClient = findViewById(R.id.button_help_client);
            mButtonHelpClient.setOnClickListener(v -> {
                MainHelpDialogFragment dialogFragment = new MainHelpDialogFragment("Допомога клієнту");
                dialogFragment.show(getSupportFragmentManager(), null);
            });

            mButtonManager = findViewById(R.id.button_manager);
            mButtonManager.setOnClickListener(v -> {
                ManagerDialogFragment dialogFragment = new ManagerDialogFragment(mPresenter.getmGoogleApiClient());
                dialogFragment.show(getSupportFragmentManager(), null);
                mPresenter.clearUnnecessaryObjects((byte) 1);
            });

            mButtonHelpManager = findViewById(R.id.button_help_manager);
            mButtonHelpManager.setOnClickListener(v -> {
                MainHelpDialogFragment dialogFragment = new MainHelpDialogFragment("Допомога менеджеру");
                dialogFragment.show(getSupportFragmentManager(), null);
            });

            mButtonEntrepreneur = findViewById(R.id.button_entrepreneur);
            mButtonEntrepreneur.setOnClickListener(v -> {
                BusinessSignInSignInDialogFragment dialogFragment = new BusinessSignInSignInDialogFragment(mPresenter.getmGoogleApiClient());
                dialogFragment.show(getSupportFragmentManager(), null);
                mPresenter.clearUnnecessaryObjects((byte) 2);
            });

            mButtonHelpBusiness = findViewById(R.id.button_help_business);
            mButtonHelpBusiness.setOnClickListener(v -> {
                MainHelpDialogFragment dialogFragment = new MainHelpDialogFragment("Допомога бізнесмену");
                dialogFragment.show(getSupportFragmentManager(), null);
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setPermission() {
        if (ContextCompat
                .checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestAllPermission();
        }
    }

    private void requestAllPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_READ_STORAGE: {
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                } else {
                    finish();
                }
                break;
            }
        }
    }

    @Override
    public void openActivity(byte code) {
        switch (code) {
            case 0: {
                startActivity(new Intent(this, ClientActivity.class));
                break;
            }
            case 1: {
                startActivity(new Intent(this, ManagerActivity.class));
                break;
            }
            case 2: {
                startActivity(new Intent(this, BusinessActivity.class));
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE: {
                mPresenter.handleResult(Auth.GoogleSignInApi.getSignInResultFromIntent(data));
                break;
            }
        }
    }
}
