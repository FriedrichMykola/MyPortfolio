package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_image_text;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.model.body_design.ImageText;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBDeleteFragment;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBDeleteImage;

import org.greenrobot.eventbus.EventBus;

import androidx.fragment.app.Fragment;

@InjectViewState
public class BEditorImageTextFragmentPresenter extends MvpPresenter<IBEditorImageTextFragmentView> {

    private ImageText mImageText;

    private int mLayout;

    public BEditorImageTextFragmentPresenter(ImageText imageText, int mLayout) {
        this.mLayout = mLayout;
        this.mImageText = imageText;
    }

    public void setmUri(Uri uri) {
        this.mImageText.setmUri(uri);
        if (mImageText.getmSUri() != null) {
            EventBus.getDefault().post(new EBDeleteImage(this.mImageText.getmSUri()));
        }

        getViewState().addImage(mImageText.getmUri());
    }

    public void deleteImage(Fragment fragment) {
        /*code is hidden*/
    }

    public int getmLayout() {
        return mLayout;
    }

    public ImageText getmImageText() {
        return mImageText;
    }

    public void restoreImage() {
        if (mImageText.getmUri() != null) {
            getViewState().addImage(mImageText.getmUri());
        }
    }
}
