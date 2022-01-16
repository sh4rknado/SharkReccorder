package com.jb.sharkreccorder.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jb.sharkreccorder.Model.FilesRecorder;
import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.R;
import com.jb.sharkreccorder.View.Adapters.FileRecorderAdapter;
import com.jb.sharkreccorder.View.Adapters.RecorderConfigurationAdapter;
import com.jb.sharkreccorder.ViewModel.FileRecorderViewModel;
import java.util.List;

public class FilesFragment extends AFragment {

    private FileRecorderViewModel filesViewModels;
    private FileRecorderAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.filesViewModels =  new ViewModelProvider(this).get(FileRecorderViewModel.class);
        this.root = inflater.inflate(R.layout.settings_recorder_fragment, container, false);

        this.adapter = new FileRecorderAdapter();

        RecyclerView recyclerView = this.root.findViewById(R.id.recycle_view_files);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.root.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        this.filesViewModels.getAllFilesRecorder().observe(this.getViewLifecycleOwner(), new Observer<List<FilesRecorder>>() {
            @Override
            public void onChanged(List<FilesRecorder> files) {
                // Update RecycleView
                adapter.setModels(files);
            }
        });
        return root;
    }
}
