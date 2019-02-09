package com.example.business.friedrich.kuzan.business.dagger;

import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.ui.business.BusinessActivityPresenter;
import com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.BodyDesignActivityPresenter;
import com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_gallery.BEditorGalleryPresenter;
import com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_image_text.BEditorImageTextFragmentPresenter;
import com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_text.BEditorOnlyTextFragmentPresenter;
import com.example.business.friedrich.kuzan.business.ui.business.design_fragment.DesignFragmentPresenter;
import com.example.business.friedrich.kuzan.business.ui.business.design_fragment.dialog_fragment.UpdateTextDialogFragmentPresenter;
import com.example.business.friedrich.kuzan.business.ui.business.main_fragment.MainBusinessFragmentPresenter;
import com.example.business.friedrich.kuzan.business.ui.main.MainActivityPresenter;
import com.example.business.friedrich.kuzan.business.ui.main.dialog.business.signIn.BusinessSignInDialogFragmentPresenter;
import com.example.business.friedrich.kuzan.business.ui.main.dialog.business.signUp.BusinessSignUpDialogFragmentPresenter;
import com.example.business.friedrich.kuzan.business.ui.main.dialog.client.signIn.ClientSignInDialogFragmentPresenter;
import com.example.business.friedrich.kuzan.business.ui.main.dialog.client.signUp.ClientSignUpDialogFragmentPresenter;
import com.example.business.friedrich.kuzan.business.ui.main.dialog.manager.ManagerDialogFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UsersModule.class,
                        FirebaseModule.class,
                        FirebaseReferenceModule.class})
public interface IDaggerComponent {
    void Inject(MyApp myApp);

    void Inject(BodyDesignActivityPresenter presenter);

    void Inject(MainActivityPresenter presenter);

    void Inject(BusinessSignInDialogFragmentPresenter presenter);

    void Inject(BusinessSignUpDialogFragmentPresenter presenter);

    void Inject(ClientSignInDialogFragmentPresenter presenter);

    void Inject(ClientSignUpDialogFragmentPresenter presenter);

    void Inject(ManagerDialogFragmentPresenter presenter);

    void Inject(MainBusinessFragmentPresenter presenter);

    void Inject(UpdateTextDialogFragmentPresenter presenter);

    void Inject(BusinessActivityPresenter presenter);

    void Inject(DesignFragmentPresenter presenter);

    void Inject(BEditorImageTextFragmentPresenter presenter);

    void Inject(BEditorOnlyTextFragmentPresenter presenter);

    void Inject(BEditorGalleryPresenter presenter);
}
