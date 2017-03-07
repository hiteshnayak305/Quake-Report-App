package com.example.android.quakereport;

/**
 * A container for items to show ass Item
 * contains--> magnitude,city_name,date
 */

public class Item {
    private double magnitude;
    private String city;
    private long date;

    public Item(double magnitude, String city, long date) {
        this.magnitude = magnitude;
        this.city = city;
        this.date = date;
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
}
