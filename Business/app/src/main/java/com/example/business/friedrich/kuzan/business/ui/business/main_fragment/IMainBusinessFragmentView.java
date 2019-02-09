package com.example.business.friedrich.kuzan.business.ui.business.main_fragment;

import android.net.Uri;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.business.friedrich.kuzan.business.model.body_design.Gallery;
import com.example.business.friedrich.kuzan.business.model.body_design.ImageText;
import com.example.business.friedrich.kuzan.business.model.body_design.OnlyText;
import com.example.business.friedrich.kuzan.business.model.enumeration.Position;

@StateStrategyType(AddToEndStrategy.class)
public interface IMainBusinessFragmentView extends MvpView {
    void changeColorBackground(int color);

    void changeColorText(int color);

    void changeColorBanner(int color);

    void changeColorBorderLogotype(int color);

    void changeLogotype(Uri uri);

    void changeBanner(Uri uri);

    void changePositionBanner(Position pos);

    void changeName(String name, int size);

    void changeSizeText(int size);

    void addOnlyTextItem(OnlyText onlyText, int counter);

    void addImageTextItem(ImageText imageText, int layout, int counter);

    void addGalleryItem(Gallery gallery, int counter);

    void showMessage(String s);

    void addPaddingBottomScrollView();

    @StateStrategyType(SingleStateStrategy.class)
    void showDialogProgress(boolean work);
}