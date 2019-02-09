package com.example.business.friedrich.kuzan.business.ui.main.dialog.business.signUp;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface IBusinessSignUpDialogFragmentView extends MvpView {
    void openBusinessActivity();
}