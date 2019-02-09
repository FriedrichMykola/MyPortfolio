package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import androidx.fragment.app.Fragment;

@StateStrategyType(SkipStrategy.class)
public interface IBodyDesignActivityView extends MvpView {

    void deleteFragment(Fragment fragment);

    void saveData();
}