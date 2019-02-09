package com.example.business.friedrich.kuzan.business.ui.main.dialog.help;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.business.friedrich.kuzan.business.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

@SuppressLint("ValidFragment")
public class MainHelpDialogFragment extends AppCompatDialogFragment {

    private TextView mTextHelp;
    private String mMessage;

    @SuppressLint("ValidFragment")
    public MainHelpDialogFragment(String message) {
        mMessage = message;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_main_help, container, false);
        mTextHelp = view.findViewById(R.id.text_help);
        mTextHelp.setText(mMessage);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
