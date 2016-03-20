package com.nirmala.android.autotracker;
// http://stackoverflow.com/questions/19978939/location-listener-works-from-a-service-but-not-an-intentservice


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

/**
 * Created by AXS43 on 3/12/2016.
 */
public class MyLocationService extends Service {
    private static final String TAG = "AutoTrackerService";

    LocationManager mLocationManager;
    MyLocationListener mLocationListener = null;
    HandlerThread mHandlerThread;
    Looper mLooper;

    public static void startService(Context context) {
        DebugLogger.getInstance().log("MyLocationService:startService");
        Intent serviceIntent = new Intent(context, MyLocationService.class);
        context.startService(serviceIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        DebugLogger.getInstance().log("MyLocationService:onStartCommand");
        Log.e(TAG, "creating handlerthread and looper");
        mHandlerThread = new HandlerThread("MyHandlerThread");
        mHandlerThread.start();
        mLooper = mHandlerThread.getLooper();

        LocationManager mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = MyLocationListener.newLocationListener(this, mLocationManager, mLooper);

        MyMessengerService.setServiceAlarm(this, true);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "in onDestroy in LocationService class");
        //mLocationManager.removeUpdates(mLocationListener);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
