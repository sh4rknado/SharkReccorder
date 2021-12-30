package com.jb.sharkreccorder.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;

import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.View.Holders.AHolder;
import com.jb.sharkreccorder.R;

public class RecorderConfigurationAdapter extends AAdapter<RecorderConfiguration, RecorderConfigurationAdapter.RecorderConfigurationHolder> {

    public RecorderConfigurationAdapter() { super(); }

    @NonNull
    @Override
    public RecorderConfigurationAdapter.RecorderConfigurationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_recorder_layout, parent, false);
        return new RecorderConfigurationAdapter.RecorderConfigurationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecorderConfigurationHolder holder, int position) {
        String[] output_formats = this.parent.getResources().getStringArray(R.array.audio_formats);
        String[] audio_sources = this.parent.getResources().getStringArray(R.array.audio_sources);

        ArrayAdapter output_format_adapter = new ArrayAdapter(this.parent.getContext(), R.layout.combobox_item, output_formats);
        holder.output_format.setAdapter(output_format_adapter);

        ArrayAdapter audio_sources_adapter = new ArrayAdapter(this.parent.getContext(), R.layout.combobox_item, audio_sources);
        holder.audio_source.setAdapter(audio_sources_adapter);

        this.modelHolders.add(holder);
    }

    static class RecorderConfigurationHolder extends AHolder {

        private final AutoCompleteTextView output_format;
        private final AutoCompleteTextView audio_source;

        public RecorderConfigurationHolder(View itemView) {
            super(itemView);
            this.output_format = itemView.findViewById(R.id.combobox_output_format);
            this.audio_source = itemView.findViewById(R.id.combobox_audio_source);
        }
    }

}