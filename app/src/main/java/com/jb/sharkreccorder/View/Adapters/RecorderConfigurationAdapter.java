package com.jb.sharkreccorder.View.Adapters;

import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;

import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.R;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.View.Holders.AHolder;

import java.util.HashMap;
import java.util.Map;

public class RecorderConfigurationAdapter extends AAdapter<RecorderConfiguration, RecorderConfigurationAdapter.RecorderConfigurationHolder> {

    private RecorderConfiguration currentConfig;
    private String[] output_formats;
    private String[] audio_sources;
    private Map<String, Integer> audioSourceDictionary;
    private Map<String, Integer> outputFormatDictionary;
    private Map<String, Integer> audioEncoderDictionary;

    public RecorderConfigurationAdapter() {
        super();
        InitializeDictionary();
    }

    private void InitializeDictionary() {
        audioSourceDictionary = new HashMap<>();
        outputFormatDictionary = new HashMap<>();
        audioEncoderDictionary = new HashMap<>();

        audioSourceDictionary.put("DEFAULT", 0);
        audioSourceDictionary.put("MIC", 1);
        audioSourceDictionary.put("VOICE_UPLINK", 2);
        audioSourceDictionary.put("VOICE_DOWNLINK", 3);
        audioSourceDictionary.put("VOICE_CALL", 4);
        audioSourceDictionary.put("CAMCORDER", 5);
        audioSourceDictionary.put("VOICE_RECOGNITION", 6);
        audioSourceDictionary.put("VOICE_COMMUNICATION", 7);
        audioSourceDictionary.put("REMOTE_SUBMIX", 8);
        audioSourceDictionary.put("UNPROCESSED", 9);
        audioSourceDictionary.put("VOICE_PERFORMANCE", 10);

        outputFormatDictionary.put("DEFAULT", 0);
        outputFormatDictionary.put("MPEG_4", 2);
        outputFormatDictionary.put("AMR_NB", 3);
        outputFormatDictionary.put("AMR_WB", 4);
        outputFormatDictionary.put("ACC_ADTS", 6);
        outputFormatDictionary.put("MPEG_2_TS", 8);
        outputFormatDictionary.put("OGG", 11);

        audioEncoderDictionary.put("DEFAULT", 0);
        audioEncoderDictionary.put("AMR_NB", 1);
        audioEncoderDictionary.put("AMR_WB", 2);
        audioEncoderDictionary.put("ACC", 3);
        audioEncoderDictionary.put("HE_AAC", 4);
        audioEncoderDictionary.put("AAC_ELD", 5);
        audioEncoderDictionary.put("VORBIS", 6);
        audioEncoderDictionary.put("OPUS", 7);
    }


    @NonNull
    @Override
    public RecorderConfigurationAdapter.RecorderConfigurationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        currentConfig = this.models.get(0);
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_recorder_layout, parent, false);
        return new RecorderConfigurationAdapter.RecorderConfigurationHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecorderConfigurationHolder holder, int position) {
        this.output_formats = this.parent.getResources().getStringArray(R.array.audio_formats);
        this.audio_sources = this.parent.getResources().getStringArray(R.array.audio_sources);

        ArrayAdapter<? extends String> output_format_adapter = new ArrayAdapter<>(this.parent.getContext(), R.layout.combobox_item, output_formats);
        holder.output_format.setAdapter(output_format_adapter);

        ArrayAdapter<? extends String> audio_sources_adapter = new ArrayAdapter<>(this.parent.getContext(), R.layout.combobox_item, audio_sources);
        holder.audio_source.setAdapter(audio_sources_adapter);

        holder.auto_start.setChecked(currentConfig.isAuto_start());
        this.modelHolders.add(holder);
    }

    @Override
    public void update(String key, Object value) {
        int index = -1;
        switch (key){
            case "audio_source":
                index = audioSourceDictionary.get(value);
                this.currentConfig.setAudioSource(index);
                break;
            case "output_format":
                index = outputFormatDictionary.get(value);
                this.currentConfig.setOutputFormat(index);
                break;
            case "auto_start":
                this.currentConfig.setAuto_start((boolean)value);
                break;
        }
        Logger.Logging(LoggerLevel.INFOS, "UPDATER RECORDER ADAPTER => ", "KEY : " + key  + " VALUE : " + value );
    }

    static class RecorderConfigurationHolder extends AHolder  {

        private final AutoCompleteTextView output_format;
        private final AutoCompleteTextView audio_source;
        private final Switch auto_start;

        public RecorderConfigurationHolder(View itemView, IObserver o) {
            super(itemView, o);

            this.output_format = itemView.findViewById(R.id.combobox_output_format);
            this.audio_source = itemView.findViewById(R.id.combobox_audio_source);
            this.auto_start = itemView.findViewById(R.id.auto_start_switch);

            this.auto_start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    update("auto_start", auto_start.isChecked());
                }
            });

            this.audio_source.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    update("audio_source", parent.getAdapter().getItem(position).toString());
                }
            });

            this.output_format.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    update("output_format", parent.getAdapter().getItem(position).toString());
                }
            });

        }

    }

    private void SetMP3() {
        currentConfig.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        currentConfig.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
    }

    private void SetWAVE() {
        currentConfig.setOutputFormat(AudioFormat.ENCODING_PCM_16BIT);
        currentConfig.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        currentConfig.setAudio_channel(1);
        currentConfig.setEncoding_rate(128000);
        currentConfig.setSample_rate(48000);
    }
}