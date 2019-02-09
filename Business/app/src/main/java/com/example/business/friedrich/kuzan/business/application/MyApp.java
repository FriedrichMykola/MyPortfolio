package com.example.business.friedrich.kuzan.business.application;

import android.app.Application;

import com.example.business.friedrich.kuzan.business.dagger.DaggerIDaggerComponent;
import com.example.business.friedrich.kuzan.business.dagger.FirebaseModule;
import com.example.business.friedrich.kuzan.business.dagger.FirebaseReferenceModule;
import com.example.business.friedrich.kuzan.business.dagger.IDaggerComponent;
import com.example.business.friedrich.kuzan.business.dagger.UsersModule;

public class MyApp extends Application {

    private static MyApp mMyApp;

    public static IDaggerComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mMyApp = this;

        mComponent = DaggerIDaggerComponent
                .builder()
                .usersModule(new UsersModule())
                .firebaseModule(new FirebaseModule())
                .firebaseReferenceModule(new FirebaseReferenceModule())
                .build();

        mComponent.Inject(this);
    }
}
