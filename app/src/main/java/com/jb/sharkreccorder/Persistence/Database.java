package com.jb.sharkreccorder.Persistence;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jb.sharkreccorder.Model.RecorderConfiguration;

@androidx.room.Database(entities = {RecorderConfiguration.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;
    public abstract IDao dao();
    private static Context context;

    public static synchronized Database getInstance(Context _context) {
        if(instance == null) {
            instance = Room.databaseBuilder(
                    _context.getApplicationContext(),
                    Database.class,
                    "SharkRecorder")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
            context = _context;
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private IDao dao;
        private PopulateDbAsyncTask(Database db) { dao = db.dao(); }

        @Override
        protected Void doInBackground(Void... voids) {

            RecorderConfiguration recorderConfiguration = new RecorderConfiguration(7, 3, 1, 100, true, 1, 128000, 48000);
            dao.insertRecorderConfiguration(recorderConfiguration);

            return null;
        }
    }

}
