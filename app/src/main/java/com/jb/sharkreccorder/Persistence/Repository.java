package com.jb.sharkreccorder.Persistence;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
import com.jb.sharkreccorder.Model.FilesRecorder;
import com.jb.sharkreccorder.Model.RecorderConfiguration;

public class Repository {

    private IDao dao;
    private final LiveData<List<RecorderConfiguration>> recorderConfigurations;
    private final LiveData<RecorderConfiguration> currentConfigurations;
    private final LiveData<List<FilesRecorder>> fileConfigurations;

    public Repository(Application application) {
        Database database = Database.getInstance(application);
        this.dao = database.dao();
        this.recorderConfigurations = dao.getAllRecorderConfigurations();
        this.currentConfigurations = fetchCurrentConfigurations();
        this.fileConfigurations = dao.getAllFilesRecorder();
    }

    //region RecorderConfiguration
    public void insertRecorderConfiguration(RecorderConfiguration s) { new InsertRecorderConfigurationAsyncTask(dao).execute(s); }
    public void deleteRecorderConfiguration(RecorderConfiguration s) { new DeleteRecorderConfigurationAsyncTask(dao).execute(s); }
    public void deleteAllRecorderConfiguration() { new DeleteAllRecorderConfigurationAsyncTask(dao); }
    public LiveData<List<RecorderConfiguration>> getAllRecorderConfigurations() { return recorderConfigurations; }
    public LiveData<RecorderConfiguration> getCurrentConfiguration() { return currentConfigurations; }

    private LiveData<RecorderConfiguration> fetchCurrentConfigurations() { return dao.getCurrentConfiguration(); }

    private static class InsertRecorderConfigurationAsyncTask extends AsyncTask<RecorderConfiguration, Void, Void> {
        private IDao dao;

        private InsertRecorderConfigurationAsyncTask(IDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(RecorderConfiguration... files) {
            dao.insertRecorderConfiguration(files[0]);
            return null;
        }
    }

    private static class DeleteRecorderConfigurationAsyncTask extends AsyncTask<RecorderConfiguration, Void, Void> {
        private IDao dao;

        private DeleteRecorderConfigurationAsyncTask(IDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(RecorderConfiguration... recorders) {
            dao.deleteRecorderConfiguration(recorders[0]);
            return null;
        }
    }

    private static class DeleteAllRecorderConfigurationAsyncTask extends AsyncTask<RecorderConfiguration, Void, Void> {
        private IDao dao;

        private DeleteAllRecorderConfigurationAsyncTask(IDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(RecorderConfiguration... voids) {
            dao.deleteAllRecorderConfigurations();
            return null;
        }
    }
    //endregion

    //region RecorderConfiguration
    public void insertFilesConfiguration(FilesRecorder f) { new InsertFilesRecorderAsyncTask(dao).execute(f); }
    public void deleteFilesConfiguration(FilesRecorder f) { new DeleteFileRecorderAsyncTask(dao).execute(f); }
    public void deleteAllFilesConfiguration() { new DeleteAllFilesRecorderAsyncTask(dao); }
    public LiveData<List<FilesRecorder>> getAllFilesRecorders() { return this.fileConfigurations; }

    private static class InsertFilesRecorderAsyncTask extends AsyncTask<FilesRecorder, Void, Void> {
        private IDao dao;

        private InsertFilesRecorderAsyncTask(IDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(FilesRecorder... files) {
            dao.insertFilesRecorder(files[0]);
            return null;
        }
    }

    private static class DeleteFileRecorderAsyncTask extends AsyncTask<FilesRecorder, Void, Void> {
        private IDao dao;

        private DeleteFileRecorderAsyncTask(IDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(FilesRecorder... files) {
            dao.deleteFileRecorder(files[0]);
            return null;
        }
    }

    private static class DeleteAllFilesRecorderAsyncTask extends AsyncTask<FilesRecorder, Void, Void> {
        private IDao dao;

        private DeleteAllFilesRecorderAsyncTask(IDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(FilesRecorder... voids) {
            dao.deleteAllFilesRecorder();
            return null;
        }
    }
    //endregion

}
