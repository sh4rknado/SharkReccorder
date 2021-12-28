package com.jb.sharkreccorder.Persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.jb.sharkreccorder.Model.RecorderConfigurations;
import java.util.List;


@Dao
public interface IDao {

    //region RecorderConfiguration
    @Insert
    void insertRecorderConfiguration(RecorderConfigurations recorder);

    @Delete
    void deleteRecorderConfiguration(RecorderConfigurations recorder);

    @Query("DELETE FROM recorder_configurations")
    void deleteAllRecorderConfigurations();

    @Query("SELECT * FROM recorder_configurations")
    LiveData<List<RecorderConfigurations>> getAllRecorderConfigurations();
    //endregion

}
