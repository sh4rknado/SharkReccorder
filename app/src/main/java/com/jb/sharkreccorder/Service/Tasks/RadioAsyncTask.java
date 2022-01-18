package com.jb.sharkreccorder.Service.Tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.jb.sharkreccorder.Model.FilesRecorder;
import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.ExternalStorageUtils;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.Utils.Observer.ISujet;
import com.jb.sharkreccorder.ViewModel.FileRecorderViewModel;

import java.io.File;
import java.util.ArrayList;

public abstract class RadioAsyncTask extends AsyncTask<String, Integer, String> implements ISujet {

    private final BroadcastReceiver.PendingResult pendingResult;
    private final Intent intent;
    private final Context context;
    private File directory;
    private ArrayList<IObserver> observers;
    private FileRecorderViewModel fileRecorderViewModel;
    private RecorderConfiguration recorderConfiguration;


    public RadioAsyncTask(BroadcastReceiver.PendingResult pendingResult, Intent intent, FileRecorderViewModel filesVM,
                          RecorderConfiguration configuration, Context context, File dir, IObserver o)
    {
        this.pendingResult = pendingResult;
        this.intent = intent;
        this.context = context;
        this.observers = new ArrayList<>();
        this.fileRecorderViewModel = filesVM;
        this.recorderConfiguration = configuration;
        this.directory = dir;
        this.register(o);
    }


    //region PROPERTIES

    public BroadcastReceiver.PendingResult getPendingResult() { return pendingResult; }

    public Intent getIntent() { return intent; }

    public Context getContext() { return context; }

    public File getDirectory() { return directory; }

    public void setDirectory(File directory) { this.directory = directory; }

    public RecorderConfiguration getRecorderConfiguration() { return recorderConfiguration; }

    public void setRecorderConfiguration(RecorderConfiguration recorderConfiguration) { this.recorderConfiguration = recorderConfiguration; }

    //endregion

    //region  ASYNC TASK
    @Override
    protected String doInBackground(String... strings) { return ""; }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // Must call finish() so the BroadcastReceiver can be recycled.
        getPendingResult().finish();
    }
    //endregion

    //region MEDIA RECORDER

    public void SetAudioFile() {
        String audioFile = this.directory.getAbsolutePath() + "/sharkRecorder-" + Constants.DATE + ".3gp";
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_ASYNC, "RECORD FILE  : " + audioFile);
        this.recorderConfiguration.SetAudioFile(audioFile);
    }

    public void ResetRecorder() {
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_ASYNC, "STOP RECORDING");
        this.recorderConfiguration.Reset();
        Logger.Logging(LoggerLevel.INFOS, Constants.RADIO_ASYNC, "CLEAN RECORDER");
        fileRecorderViewModel.insert(new FilesRecorder(recorderConfiguration.getFilesRecorder()), FilesRecorder.class);
    }

    //endregion

    //region UPDATE RECORDER
    @Override
    public void register(IObserver o){ observers.add(o); }

    @Override
    public void unregister(IObserver o) { observers.remove(o); }

    @Override
    public void update(String key, Object value) {
        for (IObserver o: observers) {
            o.update(key, value);
        }
    }
    //endregion


}
