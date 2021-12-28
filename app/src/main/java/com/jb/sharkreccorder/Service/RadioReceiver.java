package com.jb.sharkreccorder.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.Service.Tasks.IdleAsyncTask;
import com.jb.sharkreccorder.Service.Tasks.OffHookAsyncTask;
import com.jb.sharkreccorder.Service.Tasks.RingingAsyncTask;
import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;

public class RadioReceiver extends BroadcastReceiver implements IObserver {

    private boolean wasRinging = false;
    private boolean recordstarted = false;
    private MediaRecorder recorder;
    private RecorderConfiguration recorderConfiguration;

    // Default Builder
    public RadioReceiver() {

    }

    //region PROPERTIES
    public RecorderConfiguration getRecorderConfiguration() {
        return recorderConfiguration;
    }

    public void setRecorderConfiguration(RecorderConfiguration recorderConfiguration) {
        this.recorderConfiguration = recorderConfiguration;
    }

    public boolean isWasRinging() {
        return wasRinging;
    }

    public void setWasRinging(boolean wasRinging) {
        this.wasRinging = wasRinging;
    }

    public boolean isRecordstarted() {
        return recordstarted;
    }

    public void setRecordstarted(boolean recordstarted) {
        this.recordstarted = recordstarted;
    }

    public MediaRecorder getRecorder() {
        return recorder;
    }

    public void setRecorder(MediaRecorder recorder) {
        this.recorder = recorder;
    }
    //endregion

    @Override
    public void onReceive(Context context, Intent intent) {
        final String state = GetAction(intent);
        final PendingResult pendingResult = goAsync();

        if(state == null)
            return;

        switch (state) {
            case Constants.RINGING:
                if(!isWasRinging()) {
                    Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "RADIO STATE : " + state);
                    setWasRinging(true);
                    RingingAsyncTask ringingTask = new RingingAsyncTask(pendingResult, intent, getRecorder(), context, this);
                    ringingTask.execute();
                }
            break;
            case Constants.OFFHOOK:
                if(!isRecordstarted()) {
                    Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "RADIO STATE : " + state);
                    setRecordstarted(true);
                    OffHookAsyncTask offHookTask = new OffHookAsyncTask(pendingResult, intent, getRecorder(), context, this);
                    offHookTask.execute();
                }
                break;
            case Constants.IDLE:
                if(isRecordstarted())
                {
                    Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "RADIO STATE : " + state);
                    IdleAsyncTask idleTask = new IdleAsyncTask(pendingResult, intent, getRecorder(), context, this);
                    idleTask.execute();
                    setRecordstarted(false);
                }
                setWasRinging(false);
                break;
        }
    }

    private String GetAction(Intent intent) {
        Bundle bundle = intent.getExtras();

        if(bundle == null)
            return null;

        return bundle.getString(TelephonyManager.EXTRA_STATE);
    }

    @Override
    public void update(MediaRecorder recorder) {
        this.setRecorder(recorder);
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "UPDATE RECORDER");
    }



}
