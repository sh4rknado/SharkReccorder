package com.jb.sharkreccorder.Service.Tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jb.sharkreccorder.Model.FilesRecorder;
import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.ViewModel.FileRecorderViewModel;

import java.io.File;

public class OffHookAsyncTask extends RadioAsyncTask {

    public OffHookAsyncTask(BroadcastReceiver.PendingResult pendingResult, Intent intent, FileRecorderViewModel filesVM,
                            RecorderConfiguration recorder, Context context, File dir, IObserver o)
    {
        super(pendingResult, intent, filesVM, recorder, context, dir, o);
    }

    @Override
    protected String doInBackground(String... strings) {
        Logger.Logging(LoggerLevel.INFOS, Constants.OFF_HOOK_ASYNC, "RADIO_STATE - OFF HOOK : (CALL ANSWERED)");
        return "CALL ANSWERED";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
