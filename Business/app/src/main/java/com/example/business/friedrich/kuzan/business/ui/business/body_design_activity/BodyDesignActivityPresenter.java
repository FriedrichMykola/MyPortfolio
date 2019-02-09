package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.body_design.BodyDesign;
import com.example.business.friedrich.kuzan.business.model.body_design.Gallery;
import com.example.business.friedrich.kuzan.business.model.body_design.ImageText;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.example.business.friedrich.kuzan.business.model.business.Design;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.GlobalConstants;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBDeleteFragment;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBDeleteFragmentGallery;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBDeleteImage;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBListDeleteImage;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@InjectViewState
public class BodyDesignActivityPresenter extends MvpPresenter<IBodyDesignActivityView> {

    @Inject
    @Singleton
    Business mBusiness;

    @Inject
    @Singleton
    FirebaseAuth mAuth;

    private int mIndex;
    private boolean mGallery;
    private ArrayList<BodyDesign> mBodyDesigns;

    private ArrayList<String> mListDeleteImages;

    public BodyDesignActivityPresenter() {
        /*code is hidden*/
    }

    public int incrementIndex() {
        return ++mIndex;
    }

    public int getmIndex() {
        return mIndex;
    }

    public void setmIndex(int index) {
        this.mIndex = index;
    }

    public ArrayList<BodyDesign> getmBodyDesigns() {
        return mBodyDesigns;
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void deleteFragment(EBDeleteFragment eBDeleteFragment) {
        mBodyDesigns.remove(eBDeleteFragment.getmBodyDesign());
        getViewState().deleteFragment(eBDeleteFragment.getFragment());
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void deleteImage(EBDeleteImage ebDeleteImage) {
        mListDeleteImages.add(ebDeleteImage.getmPath());
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void deleteFragment(EBDeleteFragmentGallery eBDeleteFragment) {
        mGallery = false;
    }

    public int getFrameID() {
        int id;
        switch (mIndex) {
            case 0: {
                id = R.id.frame_first_item;
                break;
            }
            case 1: {
                id = R.id.frame_second_item;
                break;
            }
            case 2: {
                id = R.id.frame_third_item;
                break;
            }
            case 3: {
                id = R.id.frame_fourth_item;
                break;
            }
            default: {
                id = R.id.frame_fifth_item;
                break;
            }
        }
        return id;
    }

    public void setmGallery(boolean gallery) {
        this.mGallery = gallery;
    }

    public boolean ismGallery() {
        return mGallery;
    }

    public void addItemDesign(BodyDesign itemDesign) {
        mBodyDesigns.add(itemDesign);
    }

    public BodyDesign getItemDesign(int index) {
        return mBodyDesigns.get(index);
    }

    public BodyDesign getLastItemDesign() {
        return mBodyDesigns.get(mBodyDesigns.size() - 1);
    }

    public void saveDesign() {
        /*code is hidden*/
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public BodyDesign nextItem() {
        return mBodyDesigns.get(mIndex++);
    }
}