package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_text;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.model.body_design.BodyDesign;
import com.example.business.friedrich.kuzan.business.model.body_design.OnlyText;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatFragment;
import com.google.android.material.button.MaterialButton;
import com.jakewharton.rxbinding.widget.RxTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import rx.Subscription;

@SuppressLint("ValidFragment")
public class BEditorOnlyTextFragment extends MvpAppCompatFragment implements IBEditorOnlyTextFragmentView {

    private MaterialButton mButton;

    private EditText mEdit;
    private Subscription mEditSub;

    private OnlyText mOnlyText;

    public BEditorOnlyTextFragment() {
    }

    public BEditorOnlyTextFragment(BodyDesign mOnlyText) {
        this.mOnlyText = (OnlyText) mOnlyText;
    }

    @InjectPresenter
    BEditorOnlyTextFragmentPresenter mPresenter;

    @ProvidePresenter
    public BEditorOnlyTextFragmentPresenter getPresenter() {
        return new BEditorOnlyTextFragmentPresenter(mOnlyText);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editor_body_text, container, false);

        mEdit = view.findViewById(R.id.edit_text_ed_body);
        mEdit.setText(mPresenter.getmOnlyText().getmText());
        mEditSub = RxTextView
            .textChanges(mEdit)
            .subscribe(s -> mPresenter.getmOnlyText().setmText(s.toString()));

        mButton = view.findViewById(R.id.button_delete_fragment);
        mButton.setOnClickListener(v -> mPresenter.delete(this));

        return view;
    }

    @Override
    public void onDestroyView() {
        mEditSub.unsubscribe();
        super.onDestroyView();
    }
}
