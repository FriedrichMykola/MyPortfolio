package com.example.business.friedrich.kuzan.business.ui.business.main_fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.ICloseDialog;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProgressDialogFragment extends MvpAppCompatDialogFragment implements ICloseDialog {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.dialog_fragment_progress, container, false);
    }

    @Override
    public void onStart() {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onStart();
    }

    @Override
    public void closeDialog() {
        dismiss();
    }
}
