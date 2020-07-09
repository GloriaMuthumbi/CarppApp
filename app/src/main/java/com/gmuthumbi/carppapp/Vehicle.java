package com.gmuthumbi.carppapp;

public class Vehicle {
    //declare private member variables
    private final int vehicleImage;
    private String vehicleTitle;
    private String vehicleDescription;

    //create a constructor for our vehicle data model
    //pass the parameters vehicleImage, vehicleTittle and vehicleDescription
    Vehicle(int vehicleImage, String vehicleTitle, String vehicleDescription) {
        this.vehicleImage = vehicleImage;
        this.vehicleTitle = vehicleTitle;
        this.vehicleDescription = vehicleDescription;
    }

    //create getters and return the specific objects
    public int getVehicleImage() {
        return vehicleImage;
    }

    public String getVehicleTitle() {
        return vehicleTitle;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }
}
