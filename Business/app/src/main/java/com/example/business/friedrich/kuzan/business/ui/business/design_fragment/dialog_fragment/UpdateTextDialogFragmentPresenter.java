package com.example.business.friedrich.kuzan.business.ui.business.design_fragment.dialog_fragment;

import android.content.res.Resources;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBLetterSize;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

@InjectViewState
public class UpdateTextDialogFragmentPresenter extends MvpPresenter<IUpdateTextDialogFragmentView> {

    @Inject
    @Singleton
    Business mBusiness;

    public UpdateTextDialogFragmentPresenter() {
        MyApp.mComponent.Inject(this);
    }

    public void updateData(String newName, String size) {
        EventBus.getDefault().post(new EBLetterSize(newName, getSizeLetter(Integer.valueOf(size))));
    }

    private int getSizeLetter(int ss) {
        /*code is hidden*/
        return 0;
    }

    public int getSelectedItem() {
        switch (mBusiness.getmDesign().getmSizeText()) {
            case 4: {
                return 0;
            }
            case 7: {
                return 1;
            }
            case 9: {
                return 2;
            }
            case 11: {
                return 3;
            }
            case 13: {
                return 4;
            }
            case 15: {
                return 5;
            }
            case 17: {
                return 6;
            }
            case 19: {
                return 7;
            }
            case 21: {
                return 8;
            }
            case 23: {
                return 9;
            }
            case 25: {
                return 10;
            }
            case 27: {
                return 11;
            }
            default: {
                return 12;
            }
        }
    }
}
