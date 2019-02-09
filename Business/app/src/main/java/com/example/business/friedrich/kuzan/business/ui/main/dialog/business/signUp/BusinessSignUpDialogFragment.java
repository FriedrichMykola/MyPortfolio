package com.example.business.friedrich.kuzan.business.ui.main.dialog.business.signUp;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatDialogFragment;
import com.example.business.friedrich.kuzan.business.ui.business.BusinessActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

public class BusinessSignUpDialogFragment extends MvpAppCompatDialogFragment
    implements IBusinessSignUpDialogFragmentView {
    private TextInputEditText mEditName;
    private AppCompatSpinner mSpinner;
    private MaterialButton mButtonSignUp;

    @InjectPresenter
    BusinessSignUpDialogFragmentPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_business_sign_up, container, false);

        mEditName = view.findViewById(R.id.edit_name_business);
        mSpinner = view.findViewById(R.id.spinner_category);
        mButtonSignUp = view.findViewById(R.id.button_sign_up_business);
        mButtonSignUp.setOnClickListener(v -> mPresenter.signUpBusiness(mEditName.getText().toString(), mSpinner.getSelectedItem().toString()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void openBusinessActivity() {
        startActivity(new Intent(getContext(), BusinessActivity.class));
        dismiss();
    }

    @Override
    public void onDestroyView() {
        if (!mPresenter.ismAddedToDB()) {
            mPresenter.noAuth();
        }

        super.onDestroyView();
    }
}
