package com.jb.sharkreccorder.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.jb.sharkreccorder.Service.Tasks.IdleAsyncTask;
import com.jb.sharkreccorder.Service.Tasks.OffHookAsyncTask;
import com.jb.sharkreccorder.Service.Tasks.RingingAsyncTask;
import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.Utils.Observer.ISujet;

import java.util.ArrayList;

public class RadioReceiver extends BroadcastReceiver implements IObserver {

    public boolean wasRinging = false;
    public boolean recordstarted = false;
    private MediaRecorder recorder;

    @Override
    public void onReceive(Context context, Intent intent) {
        final String state = GetAction(intent);
        final PendingResult pendingResult = goAsync();

        if(state == null)
            return;

        switch (state) {
            case Constants.RINGING:
                if(!wasRinging) {
                    Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "RADIO STATE : " + state);
                    wasRinging = true;
                    RingingAsyncTask ringingTask = new RingingAsyncTask(pendingResult, intent, recorder, context, this);
                    ringingTask.execute();
                }
            break;
            case Constants.OFFHOOK:
                if(!recordstarted) {
                    Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "RADIO STATE : " + state);
                    recordstarted = true;
                    OffHookAsyncTask offHookTask = new OffHookAsyncTask(pendingResult, intent, recorder, context, this);
                    offHookTask.execute();
                }
                break;
            case Constants.IDLE:
                if(recordstarted)
                {
                    Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "RADIO STATE : " + state);
                    IdleAsyncTask idleTask = new IdleAsyncTask(pendingResult, intent, recorder, context, this);
                    idleTask.execute();
                    recordstarted = false;
                }
                wasRinging = false;
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
        this.recorder = recorder;
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "UPDATE RECORDER");
    }

}
