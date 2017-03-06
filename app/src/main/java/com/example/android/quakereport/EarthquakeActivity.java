package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        // Create a fake list of earthquake locations.
        ArrayList<Item> earthquakes = new ArrayList<Item>();
        earthquakes.add(new Item(R.string.mag, R.string.san_francisco, R.string.date));
        earthquakes.add(new Item(R.string.mag, R.string.london, R.string.date));
        earthquakes.add(new Item(R.string.mag, R.string.tokyo, R.string.date));
        earthquakes.add(new Item(R.string.mag, R.string.maxico, R.string.date));
        earthquakes.add(new Item(R.string.mag, R.string.moscow, R.string.date));
        earthquakes.add(new Item(R.string.mag, R.string.paris, R.string.date));
        earthquakes.add(new Item(R.string.mag, R.string.san_francisco, R.string.date));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        ItemAdapter adapter = new ItemAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
