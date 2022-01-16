package com.jb.sharkreccorder.Utils.Logger;

import android.util.Log;

import com.jb.sharkreccorder.Utils.Constants;

public class Logger {

    public static void Logging(LoggerLevel level, String TAG, String log) {
        log = "[" + level + "] " + Constants.DATE + " - " + log;
        switch (level) {
            case DEBUG:
                Log.d(TAG, log);
                break;
            case INFOS:
                Log.i(TAG, log);
                break;
            case WARNING:
                Log.w(TAG, log);
                break;
            case ERROR:
                Log.e(TAG, log);
                break;
        }
    }
}
