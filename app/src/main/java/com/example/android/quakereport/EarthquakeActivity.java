package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    LoaderManager.LoaderCallbacks<List<Item>> loaderCallbacks = new LoaderManager.LoaderCallbacks<List<Item>>() {
        @Override
        public Loader<List<Item>> onCreateLoader(int id, Bundle args) {
            return new EarthquakeLoader(getApplicationContext(), "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10");
        }

        @Override
        public void onLoadFinished(Loader<List<Item>> loader, List<Item> data) {
            setUi(data);
        }

        @Override
        public void onLoaderReset(Loader<List<Item>> loader) {
            setUi(new ArrayList<Item>());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        getSupportLoaderManager().initLoader(1, null, loaderCallbacks);
    }

    public void setUi(List<Item> items) {

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        ItemAdapter adapter = new ItemAdapter(this, items);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

    }
}
