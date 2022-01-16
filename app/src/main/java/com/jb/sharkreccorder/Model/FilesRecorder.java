package com.jb.sharkreccorder.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.io.File;

@Entity(tableName = "files_recorder")
public class FilesRecorder extends AModel {

    @Ignore
    private File file;

    @ColumnInfo(name = "file_path")
    private String path;

    public FilesRecorder(String path)
    {
        this.setPath(path);
    }

    //region PROPERTIES

    public String getPath() { return path; }
    public File getFile() { return file; }

    public void setPath(String path) {
        this.path = path;
        this.file = new File(path);
    }

    public void setFile(File file) {
        this.file = file;
        this.path = file.getAbsolutePath();
    }

    //endregion
}
