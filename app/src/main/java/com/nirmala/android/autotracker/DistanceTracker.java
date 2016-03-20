package com.nirmala.android.autotracker;

// http://stackoverflow.com/questions/15256354/android-how-to-find-total-distance-covered-using-gps-when-continuously-moving

import android.location.Location;


/**
 * Created by AXS43 on 3/11/2016.
 */
public class DistanceTracker {
    private final static String TAG = "AutoTracker";

    private Location mPrevLocation = null;
    private int distanceTraveledPerDay;
    private int distanceTraveledPerPeriod;
    private MyLocationService context;

    public DistanceTracker(MyLocationService c) {
        context = c;
    }

    public void setNewLocation(Location newLoc) {
        float[] results = new float[3];

        if (mPrevLocation == null) {
            mPrevLocation = new Location(newLoc);
            return;
        }

        Location.distanceBetween(mPrevLocation.getLatitude(),
                mPrevLocation.getLongitude(),
                newLoc.getLatitude(),
                newLoc.getLongitude(),
                results);

        distanceTraveledPerDay += (int)results[0];
        distanceTraveledPerPeriod += (int)results[0];
        AppData.setDistanceTraveledToday(context, distanceTraveledPerDay);
        mPrevLocation = new Location(newLoc);
    }

    public int getDistanceTraveledPerDay() { return distanceTraveledPerDay; }
    public int getDistanceTraveledPerPeriod() { return distanceTraveledPerPeriod; }
}
