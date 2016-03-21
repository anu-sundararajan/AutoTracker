package com.nirmala.android.autotracker;

// http://stackoverflow.com/questions/15256354/android-how-to-find-total-distance-covered-using-gps-when-continuously-moving

import android.location.Location;


/**
 * Created by AXS43 on 3/11/2016.
 */
public class DistanceTracker {
    private final static String TAG = "AutoTracker";

    private Location mPrevLocation = null;
    private MyLocationService context;

    public DistanceTracker(MyLocationService c) {
        context = c;
    }

    public int setNewLocation(Location newLoc) {
        float[] results = new float[3];
        int distanceTraveled;

        if (mPrevLocation == null) {
            mPrevLocation = new Location(newLoc);
            return 0;
        }

        Location.distanceBetween(mPrevLocation.getLatitude(),
                mPrevLocation.getLongitude(),
                newLoc.getLatitude(),
                newLoc.getLongitude(),
                results);

        distanceTraveled = AppData.getDistanceTraveledToday(context);
        distanceTraveled += (int)results[0];
        AppData.setDistanceTraveledToday(context, distanceTraveled);
        mPrevLocation = new Location(newLoc);
        return (int)results[0];
    }
}
