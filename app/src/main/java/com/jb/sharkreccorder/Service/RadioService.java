package com.jb.sharkreccorder.Service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.ViewModel.RecorderConfigurationViewModel;

import java.util.List;

public class RadioService extends Service {

    private RadioReceiver receiver;
    private RecorderConfiguration recorderConfiguration;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "CREATE SERVICE");
    }

    private void InitializeRecorder() {
        RecorderConfigurationViewModel rcViewModel = new RecorderConfigurationViewModel(this.getApplication());
        RecorderConfiguration recorderConfig = rcViewModel.getFirstConfigurations();

        // Set default configuration
        if(recorderConfig == null)
        {
            recorderConfiguration = new RecorderConfiguration(7, 3, 1, 100);
            rcViewModel.insert(recorderConfiguration, RecorderConfiguration.class);
        }
        else
            this.recorderConfiguration = recorderConfig;
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
        InitializeRecorder();

        final IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_OUT);
        filter.addAction(Constants.ACTION_IN);
        this.receiver = new RadioReceiver();
        this.receiver.setRecorderConfiguration(recorderConfiguration);
        this.registerReceiver(this.receiver, filter);

        return Service.START_STICKY;
    }

}
