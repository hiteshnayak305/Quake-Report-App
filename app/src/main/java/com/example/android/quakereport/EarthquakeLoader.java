package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Custom Loader for earth quake data
 */

class EarthquakeLoader extends AsyncTaskLoader<List<Item>> {
    private String urlString;

    /**
     * constructor for creating new loader
     * @param context context of application activity
     * @param urlString string url to contain url
     */
    EarthquakeLoader(Context context, String urlString) {
        super(context);
        this.urlString = urlString;
    }

    /**
     * force start loading of data
     */
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * background method to getData from online resources
     * @return items decoded from json string
     */
    @Override
    public List<Item> loadInBackground() {
        List<Item> items;
        if (urlString == null || urlString.equals("")) {
            return null;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String jsonString = JsonUtils.makeHttpRequest(urlString);
        items = JsonUtils.jsonDecoder(jsonString);
        return items;
    }
}
