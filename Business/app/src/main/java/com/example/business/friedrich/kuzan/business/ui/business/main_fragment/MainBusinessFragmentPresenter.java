package com.example.business.friedrich.kuzan.business.ui.business.main_fragment;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.body_design.BodyDesign;
import com.example.business.friedrich.kuzan.business.model.body_design.Gallery;
import com.example.business.friedrich.kuzan.business.model.body_design.ImageText;
import com.example.business.friedrich.kuzan.business.model.body_design.OnlyText;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.example.business.friedrich.kuzan.business.model.business.Design;
import com.example.business.friedrich.kuzan.business.model.enumeration.TypeBody;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.IBuildBodyDesign;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.ICloseDialog;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBBanner;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBLetterSize;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBLogotype;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBPaint;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBPosition;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;

@InjectViewState
public class MainBusinessFragmentPresenter extends MvpPresenter<IMainBusinessFragmentView>
    implements IBuildBodyDesign {

    @Inject
    @Singleton
    FirebaseAuth mAuth;

    @Inject
    @Singleton
    Business mBusiness;

    @Inject
    @Singleton
    DatabaseReference mDRef;

    @Inject
    @Singleton
    StorageReference mSRef;

    private int mCounter;
    private byte mProgressLength;

    private boolean mBuilt;

    private ICloseDialog mICloseDialog;

    private TreeMap<Integer, BodyDesign> mTreeMapBodyDesign;
    private TreeMap<Integer, Uri> mTreeMapUri;

    public MainBusinessFragmentPresenter() {
        MyApp.mComponent.Inject(this);
        EventBus.getDefault().register(this);

        mBusiness.getmDesign().setmCounterSaved(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void sendColores(EBPaint eBPaint) {
        switch (eBPaint.getmId()) {
            case R.id.radio_background: {
                mBusiness.getmDesign().setmColorBackground(eBPaint.getmColor());
                getViewState().changeColorBackground(eBPaint.getmColor());
                break;
            }
            case R.id.radio_text: {
                mBusiness.getmDesign().setmColorText(eBPaint.getmColor());
                getViewState().changeColorText(eBPaint.getmColor());
                break;
            }
            case R.id.radio_banner: {
                mBusiness.getmDesign().setmColorBanner(eBPaint.getmColor());
                getViewState().changeColorBanner(eBPaint.getmColor());
                break;
            }
            case R.id.radio_circle_border: {
                mBusiness.getmDesign().setmColorBorder(eBPaint.getmColor());
                getViewState().changeColorBorderLogotype(eBPaint.getmColor());
            }
        }
    }

    public boolean downloadFirebase() {
        return mBusiness.getmDesign().getmBanner() == null && mBusiness.getmDesign().getmLogotype() != null;
    }

    public Business getmBusiness() {
        return mBusiness;
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void sendLogotype(EBLogotype eBLogotype) {
        mBusiness.getmDesign().setmLogotype("Businesses/" + mAuth.getUid() + "/" + "Design/Logotype/Image.png");
        mBusiness.getmDesign().setmUriLogotype(eBLogotype.getmUri());
        mBusiness.getmDesign().incrementmCounterSaved();
        getViewState().changeLogotype(eBLogotype.getmUri());
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void sendBanner(EBBanner eBBanner) {
        mBusiness.getmDesign().setmBanner("Businesses/" + mAuth.getUid() + "/" + "Design/Banner/Image.png");
        mBusiness.getmDesign().setmUriBanner(eBBanner.getmUri());
        mBusiness.getmDesign().incrementmCounterSaved();
        getViewState().changeBanner(eBBanner.getmUri());
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void sentPosition(EBPosition position) {
        /*code is hidden*/
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void getEBLetterSize(EBLetterSize letterSize) {
        /*code is hidden*/
    }

    @Override
    public void beginBuild() {
        if (mBusiness.getmDesign().getmBodyDesign().size() != 0) {
            getViewState().showDialogProgress(true);
        } else {
            getViewState().showDialogProgress(false);
        }
        buildBodyDesign();
    }

    private void buildBodyDesign() {
        int counter = 0;
        mProgressLength = (byte) mBusiness.getmDesign().getmBodyDesign().size();
        for (BodyDesign elemDesign : mBusiness.getmDesign().getmBodyDesign()) {
            switch (elemDesign.getmTypeBody()) {
                case BOnlyText: {
                    ++counter;
                    getViewState().addOnlyTextItem((OnlyText) elemDesign, counter);
                    break;
                }
                case BImageTextRight: {
                    ++counter;
                    getViewState().addImageTextItem((ImageText) elemDesign, R.layout.layout_body_image_text_right, counter);
                    break;
                }
                case BImageTextLeft: {
                    ++counter;
                    getViewState().addImageTextItem((ImageText) elemDesign, R.layout.layout_body_image_text_left, counter);
                    break;
                }
                case BGallery: {
                    ++counter;
                    getViewState().addGalleryItem((Gallery) elemDesign, counter);
                    break;
                }
            }
        }
        closeDialog(counter);
    }

    public void setmICloseDialog(ICloseDialog iCloseDialog) {
        this.mICloseDialog = iCloseDialog;
    }

    public void buildDesign(boolean download) {
        /*code is hidden*/

    }

    private void getDataFromObject() {
        /*code is hidden*/
    }

    public void beginBuildFromFirebase() {
        /*code is hidden*/
    }

    private void getDataFromFirebase() {
        mDRef.child("Businesses")
            .child(mAuth.getUid())
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Business business = dataSnapshot.getValue(Business.class);

                    if (business.getmDesign() != null) {
                        Design design = business.getmDesign();
                        Design mainDesign = mBusiness.getmDesign();

                        mainDesign.setmColorText(design.getmColorText());
                        getViewState().changeColorText(mainDesign.getmColorText());

                        mainDesign.setmColorBorder(design.getmColorBorder());
                        getViewState().changeColorBorderLogotype(mainDesign.getmColorBorder());

                        mainDesign.setmColorBanner(design.getmColorBanner());
                        getViewState().changeColorBanner(mainDesign.getmColorBanner());

                        mainDesign.setmColorBackground(design.getmColorBackground());
                        getViewState().changeColorBackground(mainDesign.getmColorBackground());

                        mainDesign.setmSizeText(design.getmSizeText());
                        getViewState().changeSizeText(mainDesign.getmSizeText());

                        mainDesign.setmPosition(design.getmPosition());
                        getViewState().changePositionBanner(mainDesign.getmPosition());

                        mainDesign.setmLogotype(design.getmLogotype());
                        mainDesign.setmBanner(design.getmBanner());

                        mainDesign.setmCounterImages(design.getmCounterImages());

                        mSRef.child(mainDesign.getmLogotype())
                            .getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                mainDesign.setmUriLogotype(uri);
                                getViewState().changeLogotype(uri);
                            });

                        mSRef.child(mainDesign.getmBanner())
                            .getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                mainDesign.setmUriBanner(uri);
                                getViewState().changeBanner(uri);
                            });

                        for (DataSnapshot elemData : dataSnapshot.child("mDesign").child("BodyDesign").getChildren()) {
                            TypeBody typeBody = elemData.child("mTypeBody").getValue(TypeBody.class);
                            switch (typeBody) {
                                case BOnlyText: {
                                    String key = elemData.getKey();
                                    OnlyText onlyText = elemData.getValue(OnlyText.class);
                                    mTreeMapBodyDesign.put(Integer.valueOf(key), onlyText);
                                    break;
                                }
                                case BImageTextRight:
                                case BImageTextLeft: {
                                    String key = elemData.getKey();
                                    ImageText imageText = elemData.getValue(ImageText.class);
                                    mTreeMapBodyDesign.put(Integer.valueOf(key), imageText);

                                    mSRef.child(imageText.getmSUri())
                                        .getDownloadUrl()
                                        .addOnSuccessListener(uri -> {
                                            ++mCounter;
                                            imageText.setmUri(uri);
                                            if ((mCounter == mainDesign.getmCounterImages()) && (!mBuilt)) {
                                                mBuilt = true;
                                                beginBuildFromFirebase();
                                            }
                                        });
                                    break;
                                }
                                case BGallery: {
                                    String key = elemData.getKey();
                                    Gallery gallery = elemData.getValue(Gallery.class);
                                    mainDesign.getmBodyDesign().add(gallery);
                                    mTreeMapBodyDesign.put(Integer.valueOf(key), gallery);

                                    gallery.setmUris(new ArrayList<>());
                                    for (String sUri : gallery.getmSUris()) {
                                        mSRef.child(sUri)
                                            .getDownloadUrl()
                                            .addOnSuccessListener(uri -> {
                                                mTreeMapUri.put(gallery.getmSUris().indexOf(sUri), uri);
                                                ++mCounter;
                                                if ((mCounter == mainDesign.getmCounterImages()) && (!mBuilt)) {
                                                    mBuilt = true;
                                                    beginBuildFromFirebase();
                                                }
                                            });
                                    }
                                    break;
                                }
                            }
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void closeDialog(int counter) {
        if ((counter == mProgressLength) && (mICloseDialog != null)) {
            mICloseDialog.closeDialog();
        }
    }
}