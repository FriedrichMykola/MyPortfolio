package com.example.business.friedrich.kuzan.business.ui.main.dialog.client.signUp;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface IClientSignUpDialogFragmentView extends MvpView {
    void showMessage(String message);

    void openClientActivity();
}
