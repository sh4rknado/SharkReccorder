package com.jb.sharkreccorder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.jb.sharkreccorder.Model.AModel;
import com.jb.sharkreccorder.Model.RecorderConfiguration;

import java.util.List;
import java.util.Objects;

public class RecorderConfigurationViewModel extends AViewModel {

    private LiveData<List<RecorderConfiguration>> recorders;

    public RecorderConfigurationViewModel(@NonNull Application application) {
        super(application);
        this.recorders = repository.getAllRecorderConfigurations();
    }

    public LiveData<List<RecorderConfiguration>> getAllRecorderConfigurations () {
        return recorders;
    }

    public void deleteSelectedRecorderConfiguration(List<RecorderConfiguration> recorders) {
        for (RecorderConfiguration rc: recorders) this.delete(rc, RecorderConfiguration.class);
    }

    public void updateModel(AModel model) {
        for (RecorderConfiguration rc: Objects.requireNonNull(recorders.getValue())) {
            if(rc.getId().equals(model.getId())) {
                this.delete(rc, RecorderConfiguration.class);
                this.insert(model, RecorderConfiguration.class);
                return;
            }
        }
    }

    public LiveData<RecorderConfiguration> getFirstConfigurations() {
       return repository.getCurrentConfiguration();
    }

}
