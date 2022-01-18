package com.jb.sharkreccorder.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.jb.sharkreccorder.Model.AModel;
import com.jb.sharkreccorder.Model.FilesRecorder;
import com.jb.sharkreccorder.Model.RecorderConfiguration;
import com.jb.sharkreccorder.Persistence.Repository;

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

        if(FilesRecorder.class.equals(cls))
            repository.insertFilesConfiguration((FilesRecorder) model);

    }

    public void delete(AModel model, Class<?> cls) {
        // Fast Return
        if(cls == null)
            return;

        if (RecorderConfiguration.class.equals(cls))
            repository.deleteRecorderConfiguration((RecorderConfiguration) model);

        if(FilesRecorder.class.equals(cls))
            repository.deleteFilesConfiguration((FilesRecorder) model);

    }

    public void deleteAll(Class<?> cls) {

        // Fast Return
        if(cls == null)
            return;

        if (RecorderConfiguration.class.equals(cls))
            repository.deleteAllRecorderConfiguration();

        if(FilesRecorder.class.equals(cls))
            repository.deleteAllFilesConfiguration();

    }

    public void deleteSelectedModel(List<AModel> models, Class<?> cls) {
        for (AModel m: models)
            this.delete(m, cls);
    }

    public void updateModel(AModel model) throws Exception {
        throw new Exception("Not Implemented");
    }

}
