package com.jb.sharkreccorder.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.jb.sharkreccorder.Model.*;
import com.jb.sharkreccorder.Persistence.*;
import java.util.List;


public class AViewModel extends AndroidViewModel {

    public Repository repository;

    public AViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insert(AModel model, Class<?> cls) {
        // Fast Return
        if(cls == null)
            return;

        if (RecorderConfiguration.class.equals(cls))
            repository.insertRecorderConfiguration((RecorderConfiguration) model);
    }

    public void delete(AModel model, Class<?> cls) {
        // Fast Return
        if(cls == null)
            return;

        if (RecorderConfiguration.class.equals(cls))
            repository.deleteRecorderConfiguration((RecorderConfiguration) model);

    }

    public void deleteAll(Class<?> cls) {

        // Fast Return
        if(cls == null)
            return;

        if (RecorderConfiguration.class.equals(cls))
            repository.deleteAllRecorderConfiguration();

    }

    public void deleteSelectedModel(List<AModel> models, Class<?> cls) {
        for (AModel m: models)
            this.delete(m, cls);
    }

    public void updateModel(AModel model) throws Exception {
        throw new Exception("Not Implemented");
    }

}
