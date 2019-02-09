package com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus;

import com.example.business.friedrich.kuzan.business.model.body_design.BodyDesign;

import androidx.fragment.app.Fragment;

public class EBDeleteFragment {
    private Fragment mFragment;
    private BodyDesign mBodyDesign;

    public EBDeleteFragment(Fragment fragment, BodyDesign bodyDesign) {
        this.mBodyDesign = bodyDesign;
        this.mFragment = fragment;
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public BodyDesign getmBodyDesign() {
        return mBodyDesign;
    }

    public void setmBodyDesign(BodyDesign mBodyDesign) {
        this.mBodyDesign = mBodyDesign;
    }
}
