package com.gmuthumbi.carppapp.Modals;

import android.net.Uri;

public class myCar {
    private Uri carImg;
    private String carName;
    private String carPlate;

    public myCar(Uri carImg, String carName, String carPlate) {
        this.carImg = carImg;
        this.carName = carName;
        this.carPlate = carPlate;
    }

    public Uri getCarImg() {
        return carImg;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarPlate() {
        return carPlate;
    }
}
