package com.jb.sharkreccorder.Service.Tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.jb.sharkreccorder.Model.FilesRecorder;
import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.ViewModel.FileRecorderViewModel;

import java.io.File;

public class RingingAsyncTask extends RadioAsyncTask {

    public RingingAsyncTask(BroadcastReceiver.PendingResult pendingResult, Intent intent, FileRecorderViewModel filesVM,
                            RecorderConfiguration recorder, Context context, File dir, IObserver o)
    {
        super(pendingResult, intent, filesVM, recorder, context, dir, o);
    }

    @Override
    protected String doInBackground(String... strings) {
        Bundle bundle = this.getIntent().getExtras();

        String savedNumber = bundle.getString("android.intent.extra.PHONE_NUMBER");
        Logger.Logging(LoggerLevel.INFOS, Constants.RINGING_ASYNC, "CALL RECEIVED => SAVED NUMBER : " + savedNumber);

        String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        Logger.Logging(LoggerLevel.INFOS, Constants.RINGING_ASYNC, "CALL RECEIVED => CALLER : " + number);

        Logger.Logging(LoggerLevel.INFOS, Constants.RINGING_ASYNC, "INITIALIZE RECORDER");

        this.SetAudioFile();
        MediaRecorder recorder = getRecorderConfiguration().getMediaRecorderInitialized();

        try {
            recorder.prepare();
            recorder.start();
            Logger.Logging(LoggerLevel.INFOS, Constants.RINGING_ASYNC, "STARTING RECORDER");
            this.update(Constants.MEDIA_RECORDER, recorder);
        }
        catch (Exception ex){
            ex.printStackTrace();
            Logger.Logging(LoggerLevel.INFOS, Constants.RINGING_ASYNC, "Error when start : " + ex.getMessage() + " with the reason : " + ex.getCause());
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
