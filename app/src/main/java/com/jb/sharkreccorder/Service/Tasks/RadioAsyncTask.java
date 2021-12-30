package com.jb.sharkreccorder.Service.Tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Environment;

import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.Utils.Observer.ISujet;

import java.io.File;
import java.util.ArrayList;
import java.util.Observer;

public abstract class RadioAsyncTask extends AsyncTask<String, Integer, String> implements ISujet {

    private final BroadcastReceiver.PendingResult pendingResult;
    private final Intent intent;
    private final Context context;
    private File directory;
    private ArrayList<IObserver> observers;
    private RecorderConfiguration recorderConfiguration;
    public final String TAG = "RadioAsyncTask";

    public RadioAsyncTask(BroadcastReceiver.PendingResult pendingResult, Intent intent, RecorderConfiguration configuration, Context context, IObserver o) {
        this.pendingResult = pendingResult;
        this.intent = intent;
        this.context = context;
        this.observers = new ArrayList<>();
        this.recorderConfiguration = configuration;
        this.register(o);
    }

    //region PROPERTIES

    public BroadcastReceiver.PendingResult getPendingResult() {
        return pendingResult;
    }

    public Intent getIntent() {
        return intent;
    }

    public Context getContext() {
        return context;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public RecorderConfiguration getRecorderConfiguration() {
        return recorderConfiguration;
    }

    public void setRecorderConfiguration(RecorderConfiguration recorderConfiguration) {
        this.recorderConfiguration = recorderConfiguration;
    }

    //endregion

    //region  ASYNC TASK
    @Override
    protected String doInBackground(String... strings) {
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // Must call finish() so the BroadcastReceiver can be recycled.
        getPendingResult().finish();
    }
    //endregion

    //region MEDIA RECORDER

    public void SetAudioFile() {
         this.directory = new File(getContext().getFilesDir(), "SharkRecorder");

        if(!getDirectory().exists())
        {
            boolean success = getDirectory().mkdirs();
            Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "CREATE DIRECTORY RECORDER : " + success);
        }

        String audioFile = getDirectory().getAbsolutePath() + "/sharkRecorder-" + Constants.DATE + ".3gp";
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_TAG, "RECORD FILE  : " + audioFile);
        this.recorderConfiguration.SetAudioFile(audioFile);
    }

    public void ResetRecorder() {
        Logger.Logging(LoggerLevel.INFOS, TAG, "STOP RECORDING");
        this.recorderConfiguration.Reset();
        Logger.Logging(LoggerLevel.INFOS, TAG, "CLEAN RECORDER");
    }

    //endregion

    //region UPDATE RECORDER
    @Override
    public void register(IObserver o){ observers.add(o); }

    @Override
    public void unregister(IObserver o) { observers.remove(o); }

    @Override
    public void update(MediaRecorder recorder) {
        for (IObserver o: observers) {
            o.update(recorder);
        }
    }
    //endregion


}
