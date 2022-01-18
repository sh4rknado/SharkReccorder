package com.jb.sharkreccorder.Model;

import android.media.MediaRecorder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.jb.sharkreccorder.Utils.Constants;
import com.jb.sharkreccorder.Utils.Converters.DateConverter;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;

import org.apache.commons.net.io.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ColumnInfo(name = "auto_start")
    private boolean auto_start;

    @ColumnInfo(name = "audio_channel")
    private int audio_channel;

    @ColumnInfo(name = "encoding_rate")
    private int encoding_rate;

    @ColumnInfo(name = "sample_rate")
    private int sample_rate;

    @Ignore
    private MediaRecorder mediaRecorder;

    @Ignore
    private FilesRecorder filesRecorder;

    //endregion

    //region PROPERTIES
    public int getAudioSource() { return audioSource; }
    public void setAudioSource(int audioSource) { this.audioSource = audioSource; }

    public int getOutputFormat() { return outputFormat; }
    public void setOutputFormat(int outputFormat) { this.outputFormat = outputFormat; }

    public int getAudioEncoder() { return audioEncoder; }
    public void setAudioEncoder(int audioEncoder) { this.audioEncoder = audioEncoder; }

    public int getAudio_channel() { return audio_channel; }
    public void setAudio_channel(int audio_channel) { this.audio_channel = audio_channel; }

    public int getEncoding_rate() { return encoding_rate; }
    public void setEncoding_rate(int encoding_rate) { this.encoding_rate = encoding_rate; }

    public int getSample_rate() { return sample_rate; }
    public void setSample_rate(int sample_rate) { this.sample_rate = sample_rate; }

    public int getGains() { return gains; }
    public void setGains(int gains) { this.gains = gains; }

    public boolean isAuto_start() { return auto_start; }
    public void setAuto_start(boolean current) { auto_start = current; }

    public MediaRecorder getCurrentMediaRecorder() { return mediaRecorder; }

    public MediaRecorder getMediaRecorderInitialized() {
        try {
            this.mediaRecorder.setAudioSource(audioSource);
            this.mediaRecorder.setOutputFormat(outputFormat);
            this.mediaRecorder.setAudioEncoder(audioEncoder);
            this.mediaRecorder.setAudioChannels(audio_channel);
            this.mediaRecorder.setAudioEncodingBitRate(encoding_rate);
            this.mediaRecorder.setAudioSamplingRate(sample_rate);
            return mediaRecorder;
        }
        catch (Exception ex){
            ex.printStackTrace();
            Logger.Logging(LoggerLevel.ERROR, Constants.RECORDER_CONFIG, "Error when initialize MediaRecorder : " + ex.getMessage() + " with the reason : " + ex.getCause());
            return null;
        }
    }
    public void setMediaRecorder(MediaRecorder mediaRecorder) { this.mediaRecorder = mediaRecorder; }

    public FilesRecorder getFilesRecorder() { return filesRecorder; }
    public void setFilesRecorder(FilesRecorder filesRecorder) { this.filesRecorder = filesRecorder; }

    //endregion

    public RecorderConfiguration(int audioSource, int outputFormat, int audioEncoder, int gains, boolean auto_start, int audio_channel, int encoding_rate, int sample_rate) {
        this.audioSource = audioSource;
        this.outputFormat = outputFormat;
        this.audioEncoder = audioEncoder;
        this.gains = gains;
        this.auto_start = auto_start;
        this.audio_channel = audio_channel;
        this.encoding_rate = encoding_rate;
        this.sample_rate = sample_rate;
        this.mediaRecorder =  new MediaRecorder();
        this.filesRecorder = new FilesRecorder();
    }

    @Ignore
    public RecorderConfiguration(int audioSource, int outputFormat, int audioEncoder, int gains, boolean auto_start, int audio_channel, int encoding_rate, int sample_rate, FilesRecorder file) {
        this.audioSource = audioSource;
        this.outputFormat = outputFormat;
        this.audioEncoder = audioEncoder;
        this.gains = gains;
        this.auto_start = auto_start;
        this.audio_channel = audio_channel;
        this.encoding_rate = encoding_rate;
        this.sample_rate = sample_rate;
        this.mediaRecorder =  new MediaRecorder();
        this.filesRecorder = file;
    }

    public  void SetAudioFile(String file) {
        this.mediaRecorder.setOutputFile(file);
        this.filesRecorder.setPath(file);
        this.filesRecorder.setDate_start(DateConverter.GetDateNow());
    }

    public void Reset(){
        this.mediaRecorder.stop();
        this.filesRecorder.setDate_end(DateConverter.GetDateNow());
        // this.mediaRecorder.reset();   // You can reuse the object by going back to setAudioSource() step
        //this.mediaRecorder.release(); // Now the object cannot be reused
    }

}
