package com.example.business.friedrich.kuzan.business.ui.main.dialog.client.signIn;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface IClientSignInDialogFragmentView extends MvpView {
    void openClientActivity();

    void signUpClient();

    void closeDialog();

    void showMessage(String ss);

    void saveType(int type);
}