package com.example.android.quakereport;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
            //set progress bar invisible when finished loading
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
            progressBar.setVisibility(View.GONE);
            //set no earthquake text visible when data array is empty
            if (data == null || data.isEmpty()) {
                TextView noEq = (TextView) findViewById(R.id.no_eq);
                noEq.setVisibility(View.VISIBLE);
            }
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

        //set no earthquake text invisible
        TextView noEq = (TextView) findViewById(R.id.no_eq);
        noEq.setVisibility(View.GONE);

        //set no internet text invisible
        TextView noInet = (TextView) findViewById(R.id.no_inet);
        noInet.setVisibility(View.GONE);

        //set progress bar invisible when finished loading
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        //get internet status
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        if (isConnected) {
            getSupportLoaderManager().initLoader(1, null, loaderCallbacks);
            //set progress bar visible when connectivity found
            progressBar.setVisibility(View.VISIBLE);
        } else {
            noInet.setVisibility(View.VISIBLE);
        }
    }

    /**
     * method to set ui
     *
     * @param items items to be loaded to ui
     */
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
