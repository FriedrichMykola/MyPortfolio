package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_gallery;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.body_design.Gallery;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBDeleteFragmentGallery;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBDeleteImage;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.fragment.app.Fragment;

@InjectViewState
public class BEditorGalleryPresenter extends MvpPresenter<IBEditorGalleryView> {

    private Gallery mGallery;
    private GalleryAdapter mAdapter;

    public BEditorGalleryPresenter(Gallery mGallery) {
        this.mGallery = mGallery;
        this.mAdapter = new GalleryAdapter(this.mGallery);
    }

    public boolean canAddImage() {
        return mGallery.getmUris().size() < 5;
    }

    public GalleryAdapter getmAdapter() {
        return mAdapter;
    }

    public void addImage(Uri uri) {
        mAdapter.setUri(uri);
    }

    public void deleteGallery(Fragment fragment) {
        /*code is hidden*/
    }
}
