package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_image_text;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.model.body_design.BodyDesign;
import com.example.business.friedrich.kuzan.business.model.body_design.ImageText;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatFragment;
import com.google.android.material.button.MaterialButton;
import com.jakewharton.rxbinding.widget.RxTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import rx.Subscription;

public class BEditorImageTextFragment extends MvpAppCompatFragment implements IBEditorImageTextFragmentView {

    private static final int REQUEST_CODE = 1803;
    private MaterialButton mButtonDelete;
    private ImageView mImage;

    private EditText mEdit;
    private Subscription mEditSub;

    private int mLayout;
    private ImageText mImageText;

    public BEditorImageTextFragment() {
    }

    @SuppressLint("ValidFragment")
    public BEditorImageTextFragment(BodyDesign bodyDesign, int layout) {
        mLayout = layout;
        mImageText = (ImageText) bodyDesign;
    }

    @InjectPresenter
    BEditorImageTextFragmentPresenter mPresenter;

    @ProvidePresenter
    BEditorImageTextFragmentPresenter getPresenter() {
        return new BEditorImageTextFragmentPresenter(mImageText, mLayout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(mPresenter.getmLayout(), container, false);

        mImage = view.findViewById(R.id.image_ed_body);
        mImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE);
        });

        mEdit = view.findViewById(R.id.edit_ed_body);
        mEdit.setText(mPresenter.getmImageText().getmText());
        mEditSub = RxTextView.textChanges(mEdit).subscribe(s -> mPresenter.getmImageText().setmText(s.toString()));

        mButtonDelete = view.findViewById(R.id.button_delete_fragment);
        mButtonDelete.setOnClickListener(v -> mPresenter.deleteImage(this));

        mPresenter.restoreImage();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE: {
                    mPresenter.setmUri(data.getData());
                    break;
                }
            }
        }

    }

    @Override
    public void addImage(Uri uri) {
        Glide.with(getContext()).asDrawable().load(uri).into(mImage);
    }

    @Override
    public void onDestroyView() {
        mEditSub.unsubscribe();
        super.onDestroyView();
    }
}
