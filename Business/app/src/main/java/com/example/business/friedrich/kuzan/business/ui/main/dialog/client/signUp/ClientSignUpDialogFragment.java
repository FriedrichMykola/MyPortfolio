package com.example.business.friedrich.kuzan.business.ui.main.dialog.client.signUp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatDialogFragment;
import com.example.business.friedrich.kuzan.business.ui.сlient.ClientActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressLint("ValidFragment")
public class ClientSignUpDialogFragment extends MvpAppCompatDialogFragment implements IClientSignUpDialogFragmentView {

    private TextInputEditText mEdit;
    private MaterialButton mButton;

    @InjectPresenter
    ClientSignUpDialogFragmentPresenter mPresenter;

    public ClientSignUpDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_client_sign_up, container, false);

        mEdit = view.findViewById(R.id.edit_phone_client);
        mButton = view.findViewById(R.id.button_sign_up_client);
        mButton.setOnClickListener(v -> {
            mPresenter.signInClient(mEdit.getText().toString().replace("+", ""),
                    getString(R.string.error_phone));
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void openClientActivity() {
        startActivity(new Intent(getContext(), ClientActivity.class));
        dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.isClientInSystem();
    }
}
