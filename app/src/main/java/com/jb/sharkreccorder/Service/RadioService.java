package com.jb.sharkreccorder.Service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import androidx.annotation.Nullable;

import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;

public class RadioService extends Service {

    private RadioReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "CREATE SERVICE");
    }

    @Override
    public void onDestroy() {
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "DESTROY SERVICE");
        this.unregisterReceiver(this.receiver);
    }

    @Deprecated
    public void onStart(Intent intent, int startId) {
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "START SERVICE");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "START COMMAND SERVICE");

        final IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_OUT);
        filter.addAction(Constants.ACTION_IN);
        this.receiver = new RadioReceiver();
        this.registerReceiver(this.receiver, filter);

        return Service.START_STICKY;
    }

}
