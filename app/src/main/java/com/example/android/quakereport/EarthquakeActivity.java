package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    //public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        CustomTask task = new CustomTask();

        task.execute("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10");
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

    private class CustomTask extends AsyncTask<String, Void, List<Item>> {

        @Override
        protected List<Item> doInBackground(String... urlStrings) {

            List<Item> items;
            if (urlStrings.length < 1 || urlStrings[0] == "") {
                return null;
            }
            String jsonString = JsonUtils.makeHttpRequest(urlStrings[0]);
            items = JsonUtils.jsonDecoder(jsonString);
            return items;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            Log.e("DONE", "" + items.size());
            if (items != null) {
                setUi(items);
            }
        }
    }
}
