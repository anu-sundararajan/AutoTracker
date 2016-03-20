package com.nirmala.android.autotracker;

import android.location.Location;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by AXS43 on 3/20/2016.
 */
public class MyLogger {
    private final static String TAG = "AutoTracker";
    private File mFile;

    public MyLogger(String fileName) {
        /* Checks if external storage is available for read and write */
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Get the directory for the user's public documents directory.
            File folder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), "AutoTracker");
            if (folder.isDirectory() || folder.mkdirs()) {
                createOrOpenFile(folder, fileName);
                return;
            }
            Log.e(TAG, "Directory not created");
        }
    }

    private void createOrOpenFile(File folder, String fileName) {
        mFile = new File(folder, fileName);
        if(!mFile.exists()) {
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, mFile.getAbsolutePath());
    }

    protected void log(String data) {
        if (mFile == null) return;
        try {
            FileWriter writer = new FileWriter(mFile, true);
            writer.append(data);
            //writer.append("\n\r");
            writer.append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
