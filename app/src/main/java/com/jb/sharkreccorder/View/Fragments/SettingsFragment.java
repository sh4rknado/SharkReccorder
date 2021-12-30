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

import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.View.Adapters.RecorderConfigurationAdapter;
import com.jb.sharkreccorder.ViewModel.RecorderConfigurationViewModel;
import com.jb.sharkreccorder.R;

import java.util.List;

public class SettingsFragment extends AFragment {

    private RecorderConfigurationViewModel configViewModel;
    private RecorderConfigurationAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.configViewModel =  new ViewModelProvider(this).get(RecorderConfigurationViewModel.class);
        this.root = inflater.inflate(R.layout.settings_recorder_fragment, container, false);

        this.adapter = new RecorderConfigurationAdapter();

        RecyclerView recyclerView = this.root.findViewById(R.id.recycle_view_settings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.root.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        this.configViewModel.getAllRecorderConfigurations().observe(this.getViewLifecycleOwner(), new Observer<List<RecorderConfiguration>>() {
            @Override
            public void onChanged(List<RecorderConfiguration> configurations) {
                // Update RecycleView
                adapter.setModels(configurations);
            }
        });
        return root;
    }
}


