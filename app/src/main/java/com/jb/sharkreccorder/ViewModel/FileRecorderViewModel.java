package com.jb.sharkreccorder.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.jb.sharkreccorder.Model.AModel;
import com.jb.sharkreccorder.Model.FilesRecorder;
import com.jb.sharkreccorder.Model.RecorderConfiguration;
import java.util.List;
import java.util.Objects;

public class FileRecorderViewModel extends AViewModel {

    private LiveData<List<FilesRecorder>> filesRecorders;

    public FileRecorderViewModel(@NonNull Application application) {
        super(application);
        this.filesRecorders = repository.getAllFilesRecorders();
    }

    public LiveData<List<FilesRecorder>> getAllFilesRecorder () {
        return filesRecorders;
    }

    public void deleteSelectedFileRecorder(List<FilesRecorder> files) {
        for (FilesRecorder f: files)
            this.delete(f, FilesRecorder.class);
    }

    public void updateModel(AModel model) {
        for (FilesRecorder f: Objects.requireNonNull(filesRecorders.getValue())) {
            if(f.getId().equals(model.getId())) {
                this.delete(f, FilesRecorder.class);
                this.insert(model, FilesRecorder.class);
                return;
            }
        }
    }

}
