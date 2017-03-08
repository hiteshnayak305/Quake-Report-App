package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
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

        final Item currentItem = (Item) getItem(position);
        if (currentItem != null) {
            TextView mag = (TextView) listItemView.findViewById(R.id.mag_view);
            //mag.setText(String.valueOf(currentItem.getMagnitude()));   //String.valueOf to get int in string value
            mag.setText(decFormatter(currentItem.getMagnitude()));

            String fullCity = currentItem.getCity();
            if (fullCity.indexOf(',') == -1) {
                TextView offset = (TextView) listItemView.findViewById(R.id.city_offset_view);
                offset.setText(R.string.near_the);

                TextView city = (TextView) listItemView.findViewById(R.id.city_name_view);
                city.setText(fullCity);
            } else {
                TextView offset = (TextView) listItemView.findViewById(R.id.city_offset_view);
                offset.setText(fullCity.substring(0, fullCity.indexOf(',') + 1));

                TextView city = (TextView) listItemView.findViewById(R.id.city_name_view);
                city.setText(fullCity.substring(fullCity.indexOf(',') + 2));
            }

            Date date1 = new Date(currentItem.getDate());
            TextView date = (TextView) listItemView.findViewById(R.id.date_view);
            //date.setText(String.valueOf(currentItem.getDate()));
            date.setText(DateFormatter(date1));

            TextView time = (TextView) listItemView.findViewById(R.id.time_view);
            time.setText(TimeFormatter(date1));

            // Set the proper background color on the magnitude circle.
            // Fetch the background from the TextView, which is a GradientDrawable.
            // already used TextView magnitudeView = (TextView) listItemView.findViewById(R.id.mag_view);
            GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

            // Get the appropriate background color based on the current earthquake magnitude
            int magnitudeColor = getMagnitudeColor((int) Math.floor(currentItem.getMagnitude()));

            //convert color resourceid to color
            magnitudeColor = ContextCompat.getColor(getContext(), magnitudeColor);
            // Set the color on the magnitude circle
            magnitudeCircle.setColor(magnitudeColor);

            //set onClicklistener to redirect to website
            listItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.getUrl()));  //action view used to view website  Uri parse url to uri
                    getContext().startActivity(intent);
                }
            });
        }
        return listItemView;
    }

    /**
     * method to extract date in format of string
     *
     * @param obj date object
     * @return return date in string format
     */
    private String DateFormatter(Date obj) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat1.format(obj);
    }

    /**
     * method to extract time in format of string
     *
     * @param obj date object
     * @return string time
     */
    private String TimeFormatter(Date obj) {
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("h:mm a");
        return dateFormat2.format(obj);
    }

    /**
     * formate decimal to x.x formate
     *
     * @param d id double typed magnitude
     * @return returns one place decimal as string
     */
    private String decFormatter(double d) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(d);
    }

    /**
     * method to return color according to magnitude
     *
     * @param mag double typed magnitude casted to int
     * @return returned color resource id
     */
    private int getMagnitudeColor(int mag) {
        int magColorId;
        switch (mag) {
            case 0:
            case 1:
                magColorId = R.color.magnitude1;
                break;
            case 2:
                magColorId = R.color.magnitude2;
                break;
            case 3:
                magColorId = R.color.magnitude3;
                break;
            case 4:
                magColorId = R.color.magnitude4;
                break;
            case 5:
                magColorId = R.color.magnitude5;
                break;
            case 6:
                magColorId = R.color.magnitude6;
                break;
            case 7:
                magColorId = R.color.magnitude7;
                break;
            case 8:
                magColorId = R.color.magnitude8;
                break;
            case 9:
                magColorId = R.color.magnitude9;
                break;
            default:
                magColorId = R.color.magnitude10plus;
        }
        return magColorId;
    }
}
