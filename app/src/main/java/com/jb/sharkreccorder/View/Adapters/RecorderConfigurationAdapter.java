package com.jb.sharkreccorder.View.Adapters;

import android.content.DialogInterface;
import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CompletionInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.NonNull;

import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.Utils.Observer.ISujet;
import com.jb.sharkreccorder.View.Holders.AHolder;
import com.jb.sharkreccorder.R;

import java.io.Console;
import java.util.Observer;

public class RecorderConfigurationAdapter extends AAdapter<RecorderConfiguration, RecorderConfigurationAdapter.RecorderConfigurationHolder> {

    private RecorderConfiguration currentConfig;

    public RecorderConfigurationAdapter() { super(); }

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
        String[] output_formats = this.parent.getResources().getStringArray(R.array.audio_formats);
        String[] audio_sources = this.parent.getResources().getStringArray(R.array.audio_sources);

        ArrayAdapter<? extends String> output_format_adapter = new ArrayAdapter<>(this.parent.getContext(), R.layout.combobox_item, output_formats);
        holder.output_format.setAdapter(output_format_adapter);

        ArrayAdapter<? extends String> audio_sources_adapter = new ArrayAdapter<>(this.parent.getContext(), R.layout.combobox_item, audio_sources);
        holder.audio_source.setAdapter(audio_sources_adapter);

        holder.auto_start.setChecked(currentConfig.isAuto_start());
        this.modelHolders.add(holder);
    }

    @Override
    public void update(String key, Object value) {
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