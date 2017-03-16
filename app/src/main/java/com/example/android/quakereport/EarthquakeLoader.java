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
     * @param context
     * @param urlString
     */
    EarthquakeLoader(Context context, String urlString) {
        super(context);
        this.urlString = urlString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * @return
     */
    @Override
    public List<Item> loadInBackground() {
        List<Item> items;
        if (urlString == null || urlString.equals("")) {
            return null;
        }
        String jsonString = JsonUtils.makeHttpRequest(urlString);
        items = JsonUtils.jsonDecoder(jsonString);
        return items;
    }
}
