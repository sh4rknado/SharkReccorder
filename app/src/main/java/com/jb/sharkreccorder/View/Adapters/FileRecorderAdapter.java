package com.jb.sharkreccorder.View.Adapters;

import android.content.Context;
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
import androidx.core.content.ContextCompat;

import com.jb.sharkreccorder.Model.CallType;
import com.jb.sharkreccorder.Model.FilesRecorder;
import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.R;
import com.jb.sharkreccorder.Utils.Converters.DateConverter;
import com.jb.sharkreccorder.Utils.Logger.Logger;
import com.jb.sharkreccorder.Utils.Logger.LoggerLevel;
import com.jb.sharkreccorder.Utils.Observer.IObserver;
import com.jb.sharkreccorder.View.Holders.AHolder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileRecorderAdapter extends AAdapter<FilesRecorder, FileRecorderAdapter.FileRecorderHolder> {

    public FileRecorderAdapter() { super(); }

    @NonNull
    @Override
    public FileRecorderAdapter.FileRecorderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_files_recorder, parent, false);
        return new FileRecorderAdapter.FileRecorderHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FileRecorderHolder holder, int position) {
        FilesRecorder currentFile = models.get(position);
        holder.FillValues(currentFile);
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

        public void FillValues(FilesRecorder file) {

            Context context =  this.itemView.getContext();
            Date date_start = file.getDateTime_start();
            Date date_end = file.getDateTime_end();

            switch (file.getCall_type()){
                case CallType.MISSING:
                    this.img_call_type.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_call_missed));
                    break;
                case CallType.INPUT:
                    this.img_call_type.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_call_received));
                    break;
                case CallType.OUTPUT:
                    this.img_call_type.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_call_out));
                    break;
            }

            this.textView_caller_name.setText(file.getCaller_name());
            this.textView_date.setText("TODO");
            this.textView_hours.setText(date_start.getHours() + " : " + date_start.getMinutes());
            this.textView_duration.setText("(" + DateConverter.Duration(date_start, date_end)+ " s)");

        }

    }

}