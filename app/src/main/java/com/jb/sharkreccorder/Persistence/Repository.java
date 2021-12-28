package com.jb.sharkreccorder.Persistence;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import com.jb.sharkreccorder.Model.*;
import java.util.List;

public class Repository {

    private IDao dao;
    private final LiveData<List<RecorderConfigurations>> recorderConfigurations;

    public Repository(Application application) {
        Database database = Database.getInstance(application);
        dao = database.dao();
        recorderConfigurations = dao.getAllRecorderConfigurations();
    }


    //region RecorderConfiguration
    public void insertRecorderConfiguration(RecorderConfigurations s) { new InsertRecorderConfigurationAsyncTask(dao).execute(s); }
    public void deleteRecorderConfiguration(RecorderConfigurations s) { new DeleteRecorderConfigurationAsyncTask(dao).execute(s); }
    public void deleteAllRecorderConfiguration() { new DeleteAllRecorderConfigurationAsyncTask(dao); }
    public LiveData<List<RecorderConfigurations>> getAllRecorderConfigurations() { return recorderConfigurations; }

    private static class InsertRecorderConfigurationAsyncTask extends AsyncTask<RecorderConfigurations, Void, Void> {
        private IDao dao;

        private InsertRecorderConfigurationAsyncTask(IDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(RecorderConfigurations... files) {
            dao.insertRecorderConfiguration(files[0]);
            return null;
        }
    }

    private static class DeleteRecorderConfigurationAsyncTask extends AsyncTask<RecorderConfigurations, Void, Void> {
        private IDao dao;

        private DeleteRecorderConfigurationAsyncTask(IDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(RecorderConfigurations... recorders) {
            dao.deleteRecorderConfiguration(recorders[0]);
            return null;
        }
    }

    private static class DeleteAllRecorderConfigurationAsyncTask extends AsyncTask<RecorderConfigurations, Void, Void> {
        private IDao dao;

        private DeleteAllRecorderConfigurationAsyncTask(IDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(RecorderConfigurations... voids) {
            dao.deleteAllRecorderConfigurations();
            return null;
        }
    }

    //endregion

}
