package com.example.business.friedrich.kuzan.business.dagger;

import com.example.business.friedrich.kuzan.business.model.Client;
import com.example.business.friedrich.kuzan.business.model.Manager;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.example.business.friedrich.kuzan.business.model.business.Design;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UsersModule {

    @Provides
    @Singleton
    public Client getClient() {
        return new Client();
    }

    @Provides
    @Singleton
    public Manager getManager() {
        return new Manager();
    }

    @Provides
    @Singleton
    public Business getBusiness() {
        return new Business(new Design());
    }
}
