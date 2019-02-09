package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_image_text;

import android.net.Uri;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface IBEditorImageTextFragmentView extends MvpView {
    void addImage(Uri mUri);
}