package com.nirmala.android.autotracker;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by AXS43 on 3/20/2016.
 */
public class DebugLogger {
    private final static String DEBUG_DATAFILE = "Debug.txt";
    private MyLogger logger;
    private static DebugLogger instance = null;

    public static DebugLogger getInstance() {
        if (instance == null) {
            instance = new DebugLogger();
        }
        return instance;
    }

    protected DebugLogger() {
        logger = new MyLogger(DEBUG_DATAFILE);
    }

    public void log(String data) {
        Date date = Calendar.getInstance().getTime();
        CharSequence timestamp = DateFormat.format("yyyy-MM-dd HH:mm:ss ", date);
        logger.log(timestamp+data);
    }
}
