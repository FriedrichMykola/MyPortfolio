package com.example.business.friedrich.kuzan.business.ui.business.design_fragment.dialog_fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UpdateTextDialogFragment extends MvpAppCompatDialogFragment implements IUpdateTextDialogFragmentView {

    @InjectPresenter
    UpdateTextDialogFragmentPresenter mPresenter;

    private TextInputEditText mEditName;
    private MaterialButton mButtonApply;
    private Spinner mSpinnerSize;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_update_text, container, false);

        mEditName = view.findViewById(R.id.edit_new_name);
        mEditName.setText(mPresenter.mBusiness.getmName());

        mSpinnerSize = view.findViewById(R.id.spinner_size);
        mSpinnerSize.setSelection(mPresenter.getSelectedItem());
        mButtonApply = view.findViewById(R.id.button_apply_text);
        mButtonApply.setOnClickListener(v -> {
            mPresenter.updateData(mEditName.getText().toString(), mSpinnerSize.getSelectedItem().toString());
            dismiss();
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
