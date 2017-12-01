package com.example.rosst.Seventh;

/**
 * Created by rosst on 29.11.2017.
 */

public class Locate {

    private double latitude;
    private double longitude;
    private String name;

    public Locate(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {

        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
