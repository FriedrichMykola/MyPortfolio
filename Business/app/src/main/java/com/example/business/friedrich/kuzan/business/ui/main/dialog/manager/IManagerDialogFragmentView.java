package com.example.business.friedrich.kuzan.business.ui.main.dialog.manager;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface IManagerDialogFragmentView extends MvpView {
    void openClientActivity();

    void showMessage(String ss);

    void saveType(int type);
}