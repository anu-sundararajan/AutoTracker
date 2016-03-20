package com.nirmala.android.autotracker;

import android.location.Location;

/**
 * Created by AXS43 on 3/11/2016.
 */
public class LocationLogger {
    private final static String LOCATION_DATAFILE = "Location.csv";
    private MyLogger logger;

    public LocationLogger() {
        logger = new MyLogger(LOCATION_DATAFILE);
    }

    public void log(Location loc) {
        String data = loc.getLatitude() + "," + loc.getLongitude();
        logger.log(data);
    }
}
