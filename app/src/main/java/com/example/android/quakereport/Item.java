package com.example.android.quakereport;

/**
 * A container for items to show ass Item
 * contains--> magnitude,city_name,date
 */

public class Item {
    private double magnitude;
    private String city;
    private long date;
    private String url;

    public Item(double magnitude, String city, long date, String url) {
        this.magnitude = magnitude;
        this.city = city;
        this.date = date;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getCity() {
        return city;
    }

    public long getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
