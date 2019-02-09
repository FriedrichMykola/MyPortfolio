package com.example.business.friedrich.kuzan.business.ui.business.design_fragment;

import android.graphics.Bitmap;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.RotateImage;
import com.example.business.friedrich.kuzan.business.model.body_design.BodyDesign;
import com.example.business.friedrich.kuzan.business.model.body_design.Gallery;
import com.example.business.friedrich.kuzan.business.model.body_design.ImageText;
import com.example.business.friedrich.kuzan.business.model.body_design.OnlyText;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.GlobalConstants;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.IApplyNewDesign;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.IBuildBodyDesign;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.ICloseDialog;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBListDeleteImage;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBPaint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.business.friedrich.kuzan.business.model.enumeration.ImportantItem.ItemBanner;
import static com.example.business.friedrich.kuzan.business.model.enumeration.ImportantItem.ItemBodyDesign;
import static com.example.business.friedrich.kuzan.business.model.enumeration.ImportantItem.ItemLogotype;

@InjectViewState
public class DesignFragmentPresenter extends MvpPresenter<IDesignFragmentView> {

    @Inject
    @Singleton
    FirebaseAuth mAuth;

    @Inject
    @Singleton
    DatabaseReference mDRef;

    @Inject
    @Singleton
    Business mBusiness;

    @Inject
    @Singleton
    StorageReference mSRef;

    private IBuildBodyDesign mIBuildBodyDesign;
    private IApplyNewDesign mIApplyNewDesign;
    private ICloseDialog mICloseDialog;

    private EBPaint mEBPaint;

    private ArrayList<String> mListDeleteImages;

    private int mProgress;

    public DesignFragmentPresenter(IApplyNewDesign iApplyNewDesign) {
        this.mIApplyNewDesign = iApplyNewDesign;
        this.mEBPaint = new EBPaint();

        mListDeleteImages = new ArrayList<>();

        MyApp.mComponent.Inject(this);
        EventBus.getDefault().register(this);
    }

    public void setmICloseDialog(ICloseDialog iCloseDialog) {
        this.mICloseDialog = iCloseDialog;
    }

    private void saveInFirebase() {
        if (mBusiness.getmDesign().getmUriLogotype() == null) {
            getViewState().showError(ItemLogotype);
            return;
        }

        if (mBusiness.getmDesign().getmUriBanner() == null) {
            getViewState().showError(ItemBanner);
            return;
        }

        final int length = mBusiness.getmDesign().getmBodyDesign().size();

        if (length == 0) {
            getViewState().showError(ItemBodyDesign);
            return;
        }

        final int counterImages = mBusiness.getmDesign().getmCounterSaved();
        mProgress = 0;

        mDRef.child("Businesses")
            .child(mAuth.getUid())
            .child("mDesign").removeValue();

        mDRef.child("Businesses")
            .child(mAuth.getUid())
            .child("mDesign")
            .setValue(mBusiness.getmDesign())
            .addOnSuccessListener(taskSnapshot -> progressFinish(counterImages));

        DatabaseReference ref = mDRef.child("Businesses")
            .child(mAuth.getUid())
            .child("mDesign")
            .child(BodyDesign.class.getSimpleName());

        subscription(mBusiness.getmDesign().getmUriLogotype(), mBusiness.getmDesign().getmLogotype());

        subscription(mBusiness.getmDesign().getmUriBanner(), mBusiness.getmDesign().getmBanner());

        ArrayList<BodyDesign> bodyDesigns = mBusiness.getmDesign().getmBodyDesign();
        for (int index = 0; index < length; ++index) {
            switch (bodyDesigns.get(index).getmTypeBody()) {
                case BOnlyText: {
                    OnlyText onlyText = (OnlyText) bodyDesigns.get(index);
                    ref.child(String.valueOf(index)).setValue(onlyText);
                    break;
                }
                case BImageTextRight:
                case BImageTextLeft: {
                    ImageText imageText = (ImageText) bodyDesigns.get(index);
                    ref.child(String.valueOf(index)).setValue(imageText);

                    Uri uri = Uri.fromFile(new File(imageText.getmUri().getLastPathSegment()));
                    if (!uri.getAuthority().equals(GlobalConstants.AUTHOR)) {
                        subscription(imageText.getmUri(), imageText.getmSUri());
                    }
                    break;
                }
                case BGallery: {
                    Gallery gallery = (Gallery) bodyDesigns.get(index);
                    ref.child(String.valueOf(index)).setValue(gallery);
                    for (int j = 0; j < gallery.getmUris().size(); ++j) {
                        if (!gallery.getmUris().get(j).getAuthority().equals(GlobalConstants.AUTHOR)) {
                            subscription(gallery.getmUris().get(j), gallery.getmSUris().get(j));
                        }
                    }
                    break;
                }
            }
        }
        progressFinish(counterImages);
    }

    private void subscription(Uri uri, String path) {
        File file = new File(uri.getLastPathSegment());
        final int MB = 1000000;

        if (file.length() < MB) {
            mSRef.child(path)
                .putFile(Uri.fromFile(file))
                .addOnSuccessListener(taskSnapshot -> {
                    ++mProgress;
                    progressFinish(mBusiness.getmDesign().getmCounterSaved());
                });
        } else {
            Observable.just(new RotateImage(uri))
                .subscribeOn(Schedulers.io())
                .map(rotateImage -> {
                    Bitmap bitmap = rotateImage.simpleRotateBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    return baos.toByteArray();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<byte[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        mSRef.child(path).putBytes(bytes)
                            .addOnSuccessListener(taskSnapshot -> {
                                ++mProgress;
                                progressFinish(mBusiness.getmDesign().getmCounterSaved());
                            });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }
    }

    private void progressFinish(int maxElements) {
        if (mProgress == maxElements) {
            mICloseDialog.closeDialog();
            mIApplyNewDesign.applyDesign();
            ++mProgress;
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void getListDeleteImages(EBListDeleteImage ebListDeleteImage) {
        mListDeleteImages.addAll(ebListDeleteImage.getmImages());
    }

    public void deleteOldImage() {
        getViewState().showDialogProgress();
        for (String path : mListDeleteImages) {
            mSRef.child(path).delete();
        }
        saveInFirebase();
    }

    public void setmIBuildBodyDesign(IBuildBodyDesign iBuildBodyDesign) {
        this.mIBuildBodyDesign = iBuildBodyDesign;
    }

    public void startBuild() {
        this.mIBuildBodyDesign.beginBuild();
    }

    public EBPaint getmEBPaint() {
        return mEBPaint;
    }

    public void setmEBPaint(EBPaint eBPaint) {
        this.mEBPaint = eBPaint;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}