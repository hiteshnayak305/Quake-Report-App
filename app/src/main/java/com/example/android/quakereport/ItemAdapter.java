package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * custom adapter for items
 */

public class ItemAdapter extends ArrayAdapter {

    public ItemAdapter(Context context, ArrayList<Item> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        Item currentItem = (Item) getItem(position);

        TextView mag = (TextView) listItemView.findViewById(R.id.mag_view);
        mag.setText(String.valueOf(currentItem.getMagnitude()));   //String.valueOf to get int in string value

        TextView city = (TextView) listItemView.findViewById(R.id.city_name_view);
        city.setText(currentItem.getCity());

        Date date1 = new Date(currentItem.getDate());
        TextView date = (TextView) listItemView.findViewById(R.id.date_view);
        //date.setText(String.valueOf(currentItem.getDate()));
        date.setText(DateFormatter(date1));

        TextView time = (TextView) listItemView.findViewById(R.id.time_view);
        time.setText(TimeFormatter(date1));
        return listItemView;
    }

    /**
     * method to extract date in format of string
     *
     * @param obj date object
     * @return return date in string format
     */
    protected String DateFormatter(Date obj) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("LLL, dd, yyyy");
        return dateFormat1.format(obj);
    }

    /**
     * method to extract time in format of string
     *
     * @param obj date object
     * @return string time
     */
    protected String TimeFormatter(Date obj) {
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("h:mm a");
        return dateFormat2.format(obj);
    }
}
