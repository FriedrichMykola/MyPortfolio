package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_gallery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.model.body_design.BodyDesign;
import com.example.business.friedrich.kuzan.business.model.body_design.Gallery;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBDeleteFragmentGallery;
import com.example.business.friedrich.kuzan.business.mvp.MvpAppCompatFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BEditorGallery extends MvpAppCompatFragment implements IBEditorGalleryView {

    private static final int REQUEST_CODE = 1804;

    private RecyclerView mRecyclerImages;
    private FloatingActionButton mFloatButtonAdd, mFloatButtonDelete;

    private Gallery mGallery;

    public BEditorGallery() {
    }

    @SuppressLint("ValidFragment")
    public BEditorGallery(BodyDesign mGallery) {
        this.mGallery = (Gallery) mGallery;
    }

    @InjectPresenter
    BEditorGalleryPresenter mPresenter;

    @ProvidePresenter
    public BEditorGalleryPresenter getPresenter() {
        return new BEditorGalleryPresenter(mGallery);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_gallery_body, container, false);

        mRecyclerImages = view.findViewById(R.id.recycler_list_images);
        mRecyclerImages.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        mRecyclerImages.setAdapter(mPresenter.getmAdapter());

        mFloatButtonAdd = view.findViewById(R.id.float_button_add_image);
        mFloatButtonAdd.setOnClickListener(v -> {
            if (mPresenter.canAddImage()) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                Toast.makeText(getContext(), getString(R.string.max_five), Toast.LENGTH_LONG).show();
            }
        });

        mFloatButtonDelete = view.findViewById(R.id.float_button_delete);
        mFloatButtonDelete.setOnClickListener(v -> mPresenter.deleteGallery(this));

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE: {
                    mPresenter.addImage(data.getData());
                    break;
                }
            }
        }
    }
}
