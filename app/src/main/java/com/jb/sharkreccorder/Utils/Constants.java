package com.jb.sharkreccorder.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {

    public static final String ACTION_IN = "android.intent.action.PHONE_STATE";
    public static final String ACTION_OUT = "android.intent.action.NEW_OUTGOING_CALL";
    public static final String ACTION_DIAL = "android.intent.action.NEW_OUTGOING_CALL";

    public static final String DATE = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());

    public static final String RADIO_TAG = "RADIO_RECEIVER";
    public static final String RECORD_SERVICE = "RECORDER";
    public static final String DEVICE_ADMIN = "DEVICE_ADMIN";
    public static final String IDLE_ASYNC_TASK = "IDLE_ASYNC_TASK";
    public static final String OFF_HOOK_ASYNC = "OFF_HOOK_ASYNC";
    public static final String RADIO_ASYNC = "RADIO_ASYNC";
    public static final String RINGING_ASYNC = "RINGING_ASYNC";
    public static final String RECORDER_CONFIG = "RECORDER_CONFIG";

    public static final String IDLE = "IDLE";
    public static final String OFFHOOK = "OFFHOOK";
    public static final String RINGING = "RINGING";

    public static final String MEDIA_RECORDER = "MEDIA_RECORDER";
}
