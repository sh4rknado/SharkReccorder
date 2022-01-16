package com.jb.sharkreccorder.Service.Tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;

public class OffHookAsyncTask extends RadioAsyncTask {

    public OffHookAsyncTask(BroadcastReceiver.PendingResult pendingResult, Intent intent, RecorderConfiguration recorder, Context context, IObserver o) {
        super(pendingResult, intent, recorder, context, o);
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
