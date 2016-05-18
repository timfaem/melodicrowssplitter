package com.example.app.models;

public class Location {

    public String name;
    public double lat;
    public double lon;

    public Location(double lat, double lon, String name) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    public Location(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", name='" + name + '\'' +
                ", lon=" + lon +
                '}';
    }
}
