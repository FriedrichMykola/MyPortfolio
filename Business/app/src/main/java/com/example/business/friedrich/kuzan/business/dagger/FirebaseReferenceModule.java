package com.example.business.friedrich.kuzan.business.dagger;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = FirebaseModule.class)
public class FirebaseReferenceModule {

    @Provides
    @Singleton
    DatabaseReference getDatabaseReference(FirebaseDatabase firebaseDatabase) {
        return firebaseDatabase.getReference();
    }

    @Provides
    @Singleton
    StorageReference getStorageReference(FirebaseStorage firebaseStorage) {
        return firebaseStorage.getReference();
    }
}