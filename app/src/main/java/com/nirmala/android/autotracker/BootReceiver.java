package com.nirmala.android.autotracker;

// http://stackoverflow.com/questions/6391902/how-to-start-an-application-on-startup

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by AXS43 on 3/12/2016.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            MyLocationService.startService(context);
            DebugLogger.getInstance().log("The phone was rebooted");
        }
    }
}
