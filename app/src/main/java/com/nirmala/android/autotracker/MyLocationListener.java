package com.nirmala.android.autotracker;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;


/**
 * Created by AXS43 on 3/11/2016.
 */
public class MyLocationListener implements LocationListener {

    private final static String TAG = "AutoTracker";
    private static final int MIN_UPDATE_INTERVAL_IN_MS = 5 * 1000; // Recommended is 5 minutes (5 * 60 * 1000)
    private static final int MIN_UPDATE_INTERVAL_IN_METERS = 5;

    private LocationLogger mLocationLogger = null;
    private DistanceTracker mDistanceTracker = null;

    public static MyLocationListener newLocationListener(MyLocationService context, LocationManager locationManager, Looper looper) {

        MyLocationListener locationListener = null;
        if (locationManager == null) return null;

        if ( ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            //ActivityCompat.requestPermissions(context, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 12345);
            //Log.e(TAG, "Requested permission to access location data");
            return null;
        }

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "No permission to access location data");
            return null;
        }

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationListener = new MyLocationListener(context);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_UPDATE_INTERVAL_IN_MS, MIN_UPDATE_INTERVAL_IN_METERS, locationListener, looper);
            DebugLogger.getInstance().log("requested Location Updates");
        }
        //else activity.updateStatusMessage("GPS not enabled");
        return locationListener;
    }

    public MyLocationListener(MyLocationService context) {
        mDistanceTracker = new DistanceTracker(context);
        mLocationLogger = new LocationLogger();
    }

    public MyLocationListener(DistanceTracker dt, LocationLogger ll) {
        mDistanceTracker = dt;
        mLocationLogger = ll;
    }

    @Override
    public void onLocationChanged(Location loc) {
        mDistanceTracker.setNewLocation(loc);
        mLocationLogger.log(loc);
        DebugLogger.getInstance().log("onLocationChanged called");
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
}
