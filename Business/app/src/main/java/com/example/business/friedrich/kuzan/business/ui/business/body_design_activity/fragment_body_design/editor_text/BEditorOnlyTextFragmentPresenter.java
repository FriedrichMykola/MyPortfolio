package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_text;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.business.friedrich.kuzan.business.model.body_design.OnlyText;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBDeleteFragment;

import org.greenrobot.eventbus.EventBus;

import androidx.fragment.app.Fragment;

@InjectViewState
public class BEditorOnlyTextFragmentPresenter extends MvpPresenter<IBEditorOnlyTextFragmentView> {

    private OnlyText mOnlyText;

    public BEditorOnlyTextFragmentPresenter(OnlyText mOnlyText) {
        this.mOnlyText = mOnlyText;
    }

    public OnlyText getmOnlyText() {
        return mOnlyText;
    }

    public void delete(Fragment fragment) {
        EventBus.getDefault().post(new EBDeleteFragment(fragment, mOnlyText));
    }
}
