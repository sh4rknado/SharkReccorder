package com.jb.sharkreccorder.Service;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;

public class DeviceAdmin extends DeviceAdminReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Logger.Logging(LoggerLevel.INFOS, "DEVICE_ADMIN", "DEVICE ADMIN RECEIVE");
    }

    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        Logger.Logging(LoggerLevel.INFOS, "DEVICE_ADMIN", "DEVICE ADMIN ENABLED");
    }

    public void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
        Logger.Logging(LoggerLevel.INFOS, "DEVICE_ADMIN", "DEVICE ADMIN DISABLED");
    }

}