package com.example.business.friedrich.kuzan.business.model;

public class Manager {
    private String mName; // Ім'я менеджера
    private String mCountry; // Країна
    private String mRegion; // Область
    private String mTown; // Місто
    private String mEmail; // Email
    private String mPassword; // Пароль

    public Manager() {
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public String getmRegion() {
        return mRegion;
    }

    public void setmRegion(String mRegion) {
        this.mRegion = mRegion;
    }

    public String getmTown() {
        return mTown;
    }

    public void setmTown(String mTown) {
        this.mTown = mTown;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}