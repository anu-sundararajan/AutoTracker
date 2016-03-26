package com.nirmala.android.autotracker;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by AXS43 on 3/12/2016.
 */
public class MyMessengerService extends IntentService {
    private static final String TAG = "AutoTracker";
    //private static final int POLL_INTERVAL = 1000 * 60 * 60 * 4; // 1 Minute = 1000 * 60 * 1
    public static final String DISTANCE_SUMMARY = "DailyDistance.csv";
    private MyLogger mDistanceLogger;

    public static void setServiceAlarm(Context context, boolean isOn) {
        int auto_send_freq = AppData.getAutoSendFreq(context);
        if (auto_send_freq == 0) {
            isOn = false; // Do not send data automatically
        }
        auto_send_freq *= (1000 * 60 * 60 * 24); // Multiplied by number of days

        Intent i = new Intent(context, MyMessengerService.class);
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        if(isOn) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(), auto_send_freq, pi);
            DebugLogger.getInstance().log("setServiceAlarm");
            AppData.setDistanceTraveledToday(context, 0); // reset the distance
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }

    public MyMessengerService() {
        super(TAG);
        mDistanceLogger = new MyLogger(DISTANCE_SUMMARY);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int dist = AppData.getDistanceTraveledToday(this);
        dist = dist / 1000; // Converting from meters to kilometers
        String phoneNo = AppData.getPhoneNumber(this);
        Date date = Calendar.getInstance().getTime();
        CharSequence timestamp = DateFormat.format("yyyy-MM-dd HH:mm:ss ", date);
        String msg = timestamp + " Distance Traveled Today = " + Integer.toString(dist) + " km";
        MyMessenger.sendMessage(phoneNo, msg);
        String logmsg = timestamp + "," + dist;
        mDistanceLogger.log(logmsg);
        AppData.setDistanceTraveledToday(this, 0); // reset the distance
    }
}
