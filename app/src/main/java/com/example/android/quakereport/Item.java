package com.example.android.quakereport;

/**
 * A container for items to show ass Item
 * contains--> magnitude,city_name,date
 */

public class Item {
    private int magnitude;
    private int city;
    private int date;

    public Item(int magnitude, int city, int date) {
        this.magnitude = magnitude;
        this.city = city;
        this.date = date;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public int getCity() {
        return city;
    }

    public int getDate() {
        return date;
    }
}
