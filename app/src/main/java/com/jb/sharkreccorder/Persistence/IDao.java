package com.jb.sharkreccorder.Persistence;

import androidx.lifecycle.LiveData;
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

    @Query("DELETE FROM RecorderConfiguration")
    void deleteAllRecorderConfigurations();

    @Query("SELECT * FROM RecorderConfiguration")
    LiveData<List<RecorderConfiguration>> getAllRecorderConfigurations();
    //endregion

}
