package com.example.business.friedrich.kuzan.business.ui.main.dialog.business.signIn;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface IBusinessSignInDialogFragmentView extends MvpView {
    void openBusinessActivity();

    void signUpEntrepreneur();

    void closeDialog();

    void showMessage(String ss);

    void saveType(int type);
}
