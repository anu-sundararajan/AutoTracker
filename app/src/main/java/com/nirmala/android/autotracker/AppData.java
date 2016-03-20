package com.nirmala.android.autotracker;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by AXS43 on 12/14/2015.
 */
public class AppData {
    private static final String PREF_PHONE_NUMBER = "phoneNumber";
    private static final String PREF_AUTO_SEND_FREQ = "autoSendFreq";
    private static final String PREF_DIST_TRAVEL_TODAY = "distTraveledToday";

    public static String getPhoneNumber(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_PHONE_NUMBER, null);
    }

    public static void setPhoneNumber(Context context, String phoneNo) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_PHONE_NUMBER, phoneNo)
                .apply();
    }

    public static int getAutoSendFreq(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(PREF_AUTO_SEND_FREQ, 0);
    }

    public static void setAutoSendFreq(Context context, int freq) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_AUTO_SEND_FREQ, freq)
                .apply();
    }

    public static int getDistanceTraveledToday(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(PREF_DIST_TRAVEL_TODAY, 0);
    }

    public static void setDistanceTraveledToday(Context context, int dist) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_DIST_TRAVEL_TODAY, dist)
                .apply();
    }
}
