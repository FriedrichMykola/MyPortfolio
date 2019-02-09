package com.example.business.friedrich.kuzan.business.ui.business.design_fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.business.friedrich.kuzan.business.model.enumeration.ImportantItem;

@StateStrategyType(SingleStateStrategy.class)
public interface IDesignFragmentView extends MvpView {
    void showDialogProgress();

    void showError(ImportantItem item);
}