package com.nirmala.android.autotracker;

// http://web.archive.org/web/20150303013635/http://mobiforge.com/design-development/sms-messaging-android
// https://thinkandroid.wordpress.com/2010/01/08/sending-sms-from-application/
// http://web.archive.org/web/20150303013635/http://mobiforge.com/design-development/sms-messaging-android
// http://stackoverflow.com/questions/6361428/how-can-i-send-sms-messages-in-the-background-using-android

// Sample Code for sending Multipart Text Msg
// http://stackoverflow.com/questions/14452808/sending-and-receiving-sms-and-mms-in-android-pre-kit-kat-android-4-4

import android.telephony.SmsManager;

/**
 * Created by AXS43 on 3/11/2016.
 */
public class MyMessenger {

    public static void sendMessage(String phoneNumber, String message) {
        if ((phoneNumber != null) && (phoneNumber.length() > 0) && (message.length() > 0)) {
            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage(phoneNumber, null, message, null, null);
            DebugLogger.getInstance().log("sendTextMessage: " + message);
        }
    }
}
