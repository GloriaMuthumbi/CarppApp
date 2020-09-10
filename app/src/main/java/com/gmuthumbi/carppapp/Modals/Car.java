package com.gmuthumbi.carppapp.Modals;

import android.net.Uri;

public class Car {
    private Uri carImg;
    private String carName;
    private String carPrice;
    private String carPlate;
    private String carId;
    private String userId;
    private String mileage;
    private String rating;
    private String description;

    public Car(Uri carImg, String carName, String carPrice, String carPlate, String carId,String userId,String mileage,String rating,String description) {
        this.carImg = carImg;
        this.carName = carName;
        this.carPrice = carPrice;
        this.carPlate = carPlate;
        this.carId = carId;
        this.description=description;
        this.userId = userId;
        this.mileage = mileage;
        this.rating = rating;
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

    public String getCarId() {
        return carId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMileage() {
        return mileage;
    }

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }
}
