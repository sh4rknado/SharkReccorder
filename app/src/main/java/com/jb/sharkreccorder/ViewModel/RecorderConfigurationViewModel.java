package com.jb.sharkreccorder.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.jb.sharkreccorder.Model.*;
import java.util.List;
import java.util.Objects;

public class RecorderConfigurationViewModel extends AViewModel {

    LiveData<List<RecorderConfigurations>> recorders;

    public RecorderConfigurationViewModel(@NonNull Application application) {
        super(application);
        recorders = repository.getAllRecorderConfigurations();
    }

    public LiveData<List<RecorderConfigurations>> getAllRecorderConfigurations () {
        return repository.getAllRecorderConfigurations();
    }

    public void deleteSelectedRecorderConfiguration(List<RecorderConfigurations> recorders) {
        for (RecorderConfigurations rc: recorders) this.delete(rc, RecorderConfigurations.class);
    }

    public void updateModel(AModel model) {
        for (RecorderConfigurations rc: Objects.requireNonNull(recorders.getValue())) {
            if(rc.getId().equals(model.getId())) {
                this.delete(rc, RecorderConfigurations.class);
                this.insert(model, RecorderConfigurations.class);
                return;
            }
        }
    }

}
