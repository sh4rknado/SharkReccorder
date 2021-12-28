package com.jb.sharkreccorder.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.jb.sharkreccorder.Model.*;
import java.util.List;
import java.util.Objects;

public class RecorderConfigurationViewModel extends AViewModel {

    LiveData<List<RecorderConfiguration>> recorders;

    public RecorderConfigurationViewModel(@NonNull Application application) {
        super(application);
        recorders = repository.getAllRecorderConfigurations();
    }

    public LiveData<List<RecorderConfiguration>> getAllRecorderConfigurations () {
        return repository.getAllRecorderConfigurations();
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

}
