package com.example.business.friedrich.kuzan.business.ui.business;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface IBusinessActivityView extends MvpView {
    void changePositionButtonDesign(int flag);
}