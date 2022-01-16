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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

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
                // index = audioSourceDictionary.get(value);
                // this.filesRecorder.setAudioSource(index);
                break;
            case "output_format":
                // index = outputFormatDictionary.get(value);
                // this.filesRecorder.setOutputFormat(index);
                break;
            case "auto_start":
                // this.filesRecorder.setAuto_start((boolean)value);
                break;
        }
        Logger.Logging(LoggerLevel.INFOS, "UPDATER RECORDER ADAPTER => ", "KEY : " + key  + " VALUE : " + value );
    }

    static class FileRecorderHolder extends AHolder  {

        private final ImageButton btn_caller;
        private final ImageView img_call_type;
        private final TextView textView_caller_name;
        private final TextView textView_date;
        private final TextView textView_hours;
        private final TextView textView_duration;

        public FileRecorderHolder(View itemView, IObserver o) {
            super(itemView, o);

            this.btn_caller = itemView.findViewById(R.id.btn_caller);
            this.img_call_type = itemView.findViewById(R.id.img_call_type);
            this.textView_caller_name = itemView.findViewById(R.id.textview_caller_name);
            this.textView_date = itemView.findViewById(R.id.textview_date);
            this.textView_hours = itemView.findViewById(R.id.textview_hours);
            this.textView_duration = itemView.findViewById(R.id.textview_duration);

            this.btn_caller.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    
                }
            });


        }

    }

}