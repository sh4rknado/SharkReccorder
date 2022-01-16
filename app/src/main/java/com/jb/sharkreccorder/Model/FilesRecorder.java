package com.jb.sharkreccorder.Model;

import android.media.AudioTimestamp;
import android.media.MediaTimestamp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.io.File;
import java.util.Date;

@Entity(tableName = "files_recorder")
public class FilesRecorder extends AModel {

    @Ignore
    private File file;

    @ColumnInfo(name = "file_path")
    private String path;

    @ColumnInfo(name = "caller_name")
    private String caller_name;

    @ColumnInfo(name = "date_start")
    private Date date_start;

    @ColumnInfo(name = "date_end")
    private Date date_end;

    public FilesRecorder(String path, String caller_name, Date date_start, Date date_end) {
        this.path = path;
        this.caller_name = caller_name;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    //region PROPERTIES

    public Date getDate_start() { return date_start; }
    public void setDate_start(Date date_start) { this.date_start = date_start; }

    public Date getDate_end() { return date_end; }
    public void setDate_end(Date date_end) { this.date_end = date_end; }

    public String getCaller_name() { return caller_name; }
    public void setCaller_name(String caller_name) { this.caller_name = caller_name; }

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
