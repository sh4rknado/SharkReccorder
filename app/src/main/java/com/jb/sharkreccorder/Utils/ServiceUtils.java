package com.jb.sharkreccorder.Utils;

import android.app.ActivityManager;

public class ServiceUtils {

    // manager = getSystemService(Context.ACTIVITY_SERVICE);
    public static boolean isRunningService(ActivityManager manager, Class<?> serviceClass) {
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
