package com.jb.sharkreccorder.Model;
import android.media.MediaRecorder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "recorder_configurations")
public class RecorderConfiguration extends AModel {

    //region ATTRIBUTES

    // MediaRecorder.AudioSource
    // DEFAULT = 0;
    // MIC = 1;
    // VOICE_UPLINK = 2;
    // VOICE_DOWNLINK = 3;
    // VOICE_CALL = 4;
    // CAMCORDER = 5;
    // VOICE_RECOGNITION = 6;
    // VOICE_COMMUNICATION = 7;
    // REMOTE_SUBMIX = 8;
    // UNPROCESSED = 9;
    // VOICE_PERFORMANCE = 10;
    @ColumnInfo(name = "audio_source")
    private int audioSource;

    // MediaRecorder.OutputFormat
    //  AAC_ADTS = 6;
    //  AMR_NB = 3;
    //  AMR_WB = 4;
    //  DEFAULT = 0;
    //  MPEG_2_TS = 8;
    //  MPEG_4 = 2;
    //  OGG = 11;
    @ColumnInfo(name = "output_format")
    private int outputFormat;

    // MediaRecorder.AudioEncoder
    // public static final int DEFAULT = 0;
    // public static final int AMR_NB = 1;
    // public static final int AMR_WB = 2;
    // public static final int AAC = 3;
    // public static final int HE_AAC = 4;
    // public static final int AAC_ELD = 5;
    // public static final int VORBIS = 6;
    // public static final int OPUS = 7;
    @ColumnInfo(name = "audio_encoder")
    private int audioEncoder;

    // 0% -> 300% (Default = 100%)
    @ColumnInfo(name = "level")
    private int gains;

    @Ignore
    private MediaRecorder mediaRecorder;
    //endregion

    //region PROPERTIES
    public int getAudioSource() { return audioSource; }
    public void setAudioSource(int audioSource) {
        this.audioSource = audioSource;
        mediaRecorder.setAudioSource(audioSource);
    }

    public int getOutputFormat() { return outputFormat; }
    public void setOutputFormat(int outputFormat) {
        this.outputFormat = outputFormat;
        this.mediaRecorder.setOutputFormat(outputFormat);
    }

    public int getAudioEncoder() { return audioEncoder; }
    public void setAudioEncoder(int audioEncoder) {
        this.audioEncoder = audioEncoder;
        this.mediaRecorder.setAudioEncoder(audioEncoder);
    }

    public int getGains() { return gains; }
    public void setGains(int gains) { this.gains = gains; }

    public MediaRecorder getMediaRecorder() { return mediaRecorder; }
    public void setMediaRecorder(MediaRecorder mediaRecorder) { this.mediaRecorder = mediaRecorder; }
    //endregion

    public RecorderConfiguration(int audioSource, int outputFormat, int audioEncoder, int gains) {
        this.mediaRecorder = new MediaRecorder();
        this.audioSource = audioSource;
        this.outputFormat = outputFormat;
        this.audioEncoder = audioEncoder;
        this.gains = gains;
    }

}
