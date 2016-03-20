package com.nirmala.android.autotracker;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by AXS43 on 3/12/2016.
 */
public class MyMessengerService extends IntentService {
    private static final String TAG = "AutoTracker";
    private static final int POLL_INTERVAL = 1000 * 60 * 60 * 4; // 1 Minute = 1000 * 60 * 1

    public static void setServiceAlarm(Context context, boolean isOn) {
        Intent i = new Intent(context, MyMessengerService.class);
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        if(isOn) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(), POLL_INTERVAL, pi);
            DebugLogger.getInstance().log("setServiceAlarm");
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }

    public MyMessengerService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int dist = AppData.getDistanceTraveledToday(this);
        String phoneNo = AppData.getPhoneNumber(this);
        String msg = "Distance Traveled Today = " + Integer.toString(dist);
        MyMessenger.sendMessage(phoneNo, msg);
        DebugLogger.getInstance().log("Called MyMessenger: " + msg);
    }
}
