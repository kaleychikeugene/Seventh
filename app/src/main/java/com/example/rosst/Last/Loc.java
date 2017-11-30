package com.example.rosst.Last;

/**
 * Created by rosst on 29.11.2017.
 */

public class Loc {
    private double lat;
    private double lon;

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {

        return lat;
    }

    public double getLon() {
        return lon;
    }

    public Loc(double lat, double lon) {

        this.lat = lat;
        this.lon = lon;
    }
}
