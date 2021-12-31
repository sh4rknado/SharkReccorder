package com.jb.sharkreccorder.Persistence;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.jb.sharkreccorder.Model.RecorderConfiguration;
import java.util.List;


@Dao
public interface IDao {

    //region RecorderConfiguration
    @Insert
    void insertRecorderConfiguration(RecorderConfiguration recorder);

    @Delete
    void deleteRecorderConfiguration(RecorderConfiguration recorder);

    @Query("DELETE FROM recorder_configurations")
    void deleteAllRecorderConfigurations();

    @Query("SELECT * FROM recorder_configurations")
    LiveData<List<RecorderConfiguration>> getAllRecorderConfigurations();

    @Query("SELECT * FROM recorder_configurations LIMIT 1")
    LiveData<List<RecorderConfiguration>> getCurrentConfiguration();

    @Query("SELECT auto_start FROM recorder_configurations")
    boolean getAutoStartConfiguration();

    //endregion

}
