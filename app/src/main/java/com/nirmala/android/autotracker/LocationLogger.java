package com.nirmala.android.autotracker;

import android.location.Location;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by AXS43 on 3/11/2016.
 */
public class LocationLogger {
    public final static String LOCATION_DATAFILE = "Location.csv";
    private MyLogger logger;

    public LocationLogger() {
        logger = new MyLogger(LOCATION_DATAFILE);
    }

    public void log(Location loc, int dist) {
        Date date = Calendar.getInstance().getTime();
        CharSequence timestamp = DateFormat.format("yyyy-MM-dd HH:mm:ss ", date);
        String data = timestamp + ", " + loc.getLatitude() + ", " + loc.getLongitude() + ", " + dist;
        logger.log(data);
    }
}
