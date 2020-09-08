package com.gmuthumbi.carppapp.Modals;

import android.net.Uri;

public class Car {
    private Uri carImg;
    private String carName;
    private String carPrice;
    private String carPlate;

    public Car(Uri carImg, String carName, String carPrice, String carPlate) {
        this.carImg = carImg;
        this.carName = carName;
        this.carPrice = carPrice;
        this.carPlate = carPlate;

    }

    public Uri getCarImg() {
        return carImg;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public String getCarPlate() {
        return carPlate;
    }
}
