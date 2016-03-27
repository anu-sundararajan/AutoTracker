package com.nirmala.android.autotracker;

import android.app.Application;
import android.content.Context;
import org.acra.*;
import org.acra.annotation.*;

// http://stackoverflow.com/questions/17463237/how-to-send-android-crash-report-using-acra
// https://www.toptal.com/android/automated-android-crash-reports-with-acra-and-cloudant
// https://github.com/ACRA/acra/wiki/BasicSetup

/**
 * Created by AXS43 on 3/27/2016.
 */

@ReportsCrashes(formKey = "", // will not be used
        mailTo = "anuradha.sundararajan@gmail.com", // my email here
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text)

public class MyAutoTracker extends Application   {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }
}
