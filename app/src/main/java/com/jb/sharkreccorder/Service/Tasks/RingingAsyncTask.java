package com.jb.sharkreccorder.Service.Tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;

public class RingingAsyncTask extends RadioAsyncTask {

    private final String TAG = "RingingAsyncTask";

    public RingingAsyncTask(BroadcastReceiver.PendingResult pendingResult, Intent intent, RecorderConfiguration recorder, Context context, IObserver o) {
        super(pendingResult, intent, recorder, context, o);
    }

    @Override
    protected String doInBackground(String... strings) {
        Bundle bundle = this.getIntent().getExtras();

        String savedNumber = bundle.getString("android.intent.extra.PHONE_NUMBER");
        Logger.Logging(LoggerLevel.INFOS, TAG, "CALL RECEIVED => SAVED NUMBER : " + savedNumber);

        String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        Logger.Logging(LoggerLevel.INFOS, TAG, "CALL RECEIVED => CALLER : " + number);

        Logger.Logging(LoggerLevel.INFOS, TAG, "INITIALIZE RECORDER");

        this.SetAudioFile();
        MediaRecorder recorder = getRecorderConfiguration().getMediaRecorder();

        try {
            recorder.prepare();
            recorder.start();
            Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "STARTING RECORDER");
            this.update(Constants.MEDIA_RECORDER, recorder);
        }
        catch (Exception ex){
            ex.printStackTrace();
            Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "Error when start : " + ex.getMessage() + " with the reason : " + ex.getCause());
            ResetRecorder();
        }

        return "INITIALIZE RECORDER";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    public void update(String key, Object value) {
        super.update(key, value);
    }

}
