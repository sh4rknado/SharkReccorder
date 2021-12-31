package com.jb.sharkreccorder.Persistence;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.jb.sharkreccorder.Model.*;
import java.util.List;

public class Repository {

    private IDao dao;
    private final LiveData<List<RecorderConfiguration>> recorderConfigurations;
    private final RecorderConfiguration currentConfigurations;

    public Repository(Application application) {
        Database database = Database.getInstance(application);
        this.dao = database.dao();
        this.recorderConfigurations = dao.getAllRecorderConfigurations();
        this.currentConfigurations = fetchCurrentConfigurations();
    }

    //region RecorderConfiguration
    public void insertRecorderConfiguration(RecorderConfiguration s) { new InsertRecorderConfigurationAsyncTask(dao).execute(s); }
    public void deleteRecorderConfiguration(RecorderConfiguration s) { new DeleteRecorderConfigurationAsyncTask(dao).execute(s); }
    public void deleteAllRecorderConfiguration() { new DeleteAllRecorderConfigurationAsyncTask(dao); }
    public LiveData<List<RecorderConfiguration>> getAllRecorderConfigurations() { return recorderConfigurations; }
    public RecorderConfiguration getCurrentConfiguration() { return currentConfigurations; }

    private RecorderConfiguration fetchCurrentConfigurations() {
        List<RecorderConfiguration> configs = dao.getCurrentConfiguration().getValue();

        if(configs == null || configs.isEmpty())
            return null;

        return configs.get(0);
    }

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

}
