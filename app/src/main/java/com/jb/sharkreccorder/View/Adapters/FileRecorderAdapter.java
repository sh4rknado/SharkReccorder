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

import com.jb.sharkreccorder.Model.FilesRecorder;
import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.R;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.View.Holders.AHolder;

import java.util.HashMap;
import java.util.Map;

public class FileRecorderAdapter extends AAdapter<FilesRecorder, FileRecorderAdapter.FileRecorderHolder> {

    private FilesRecorder filesRecorder;
    private String[] output_formats;
    private String[] audio_sources;
    private Map<String, Integer> audioSourceDictionary;
    private Map<String, Integer> outputFormatDictionary;
    private Map<String, Integer> audioEncoderDictionary;

    public FileRecorderAdapter() { super(); }

    @NonNull
    @Override
    public FileRecorderAdapter.FileRecorderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        filesRecorder = this.models.get(0);
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_recorder_layout, parent, false);
        return new FileRecorderAdapter.FileRecorderHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FileRecorderHolder holder, int position) {
        this.output_formats = this.parent.getResources().getStringArray(R.array.audio_formats);
        this.audio_sources = this.parent.getResources().getStringArray(R.array.audio_sources);

        ArrayAdapter<? extends String> output_format_adapter = new ArrayAdapter<>(this.parent.getContext(), R.layout.combobox_item, output_formats);
        holder.output_format.setAdapter(output_format_adapter);

        ArrayAdapter<? extends String> audio_sources_adapter = new ArrayAdapter<>(this.parent.getContext(), R.layout.combobox_item, audio_sources);
        holder.audio_source.setAdapter(audio_sources_adapter);

        this.modelHolders.add(holder);
    }

    @Override
    public void update(String key, Object value) {
        int index = -1;
        switch (key){
            case "audio_source":
                index = audioSourceDictionary.get(value);
                // this.filesRecorder.setAudioSource(index);
                break;
            case "output_format":
                index = outputFormatDictionary.get(value);
                // this.filesRecorder.setOutputFormat(index);
                break;
            case "auto_start":
                // this.filesRecorder.setAuto_start((boolean)value);
                break;
        }
        Logger.Logging(LoggerLevel.INFOS, "UPDATER RECORDER ADAPTER => ", "KEY : " + key  + " VALUE : " + value );
    }

    static class FileRecorderHolder extends AHolder  {

        private final AutoCompleteTextView output_format;
        private final AutoCompleteTextView audio_source;
        private final Switch auto_start;

        public FileRecorderHolder(View itemView, IObserver o) {
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

}