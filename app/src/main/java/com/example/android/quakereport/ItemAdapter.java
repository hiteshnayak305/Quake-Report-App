package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
        mag.setText(String.valueOf(currentItem.getMagnitude()));
        TextView city = (TextView) listItemView.findViewById(R.id.city_name_view);
        city.setText(currentItem.getCity());
        TextView date = (TextView) listItemView.findViewById(R.id.date_view);
        date.setText(String.valueOf(currentItem.getDate()));
        return listItemView;
    }
}
