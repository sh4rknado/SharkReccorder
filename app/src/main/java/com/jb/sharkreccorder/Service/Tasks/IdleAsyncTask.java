package com.jb.sharkreccorder.Service.Tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.AsyncTask;

import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;

import java.io.File;


public class IdleAsyncTask extends RadioAsyncTask {

    private final String TAG = "IDLE_ASYNC_TASK";

    public IdleAsyncTask(BroadcastReceiver.PendingResult pendingResult, Intent intent, MediaRecorder recorder, Context context, IObserver o) {
        super(pendingResult, intent, recorder, context, o);
    }

    @Override
    protected String doInBackground(String... strings) {
        Logger.Logging(LoggerLevel.INFOS, TAG, "CALLING STATE : IDLE");
        MediaRecorder recorder = getRecorder();

        if(recorder != null) {
            ResetRecorder();
        }
        return "STOP RECORDING";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    public void update(MediaRecorder recorder) {
        super.update(recorder);
    }

}
