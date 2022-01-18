package com.jb.sharkreccorder.Model;

import android.media.AudioTimestamp;
import android.media.MediaTimestamp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.jb.sharkreccorder.Utils.Converters.DateConverter;

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
    private String date_start;

    @ColumnInfo(name = "date_end")
    private String date_end;

    //public static final int MISSING = 0;
    //public static final int INPUT = 1;
    //public static final int OUTPUT = 2;
    @ColumnInfo(name = "call_type")
    private int call_type;

    @Ignore
    public FilesRecorder() { }

    @Ignore
    public FilesRecorder(FilesRecorder file) { CopyFrom(file); }

    public FilesRecorder(String path, String caller_name, String date_start, String date_end, int call_type) {
        this.path = path;
        this.caller_name = caller_name;
        this.date_start = date_start;
        this.date_end = date_end;
        this.call_type = call_type;
    }

    //region PROPERTIES

    public String getDate_start() { return this.date_start; }
    public void setDate_start(String date) { this.date_start = date; }

    public String getDate_end() { return date_end; }
    public void setDate_end(String date) { this.date_end = date; }

    //region DATE_UTILS
    public Date getDateTime_start() { return DateConverter.toDate(date_start); }
    public void setDateTime_start(Date date) { this.date_start = DateConverter.toTimestamp(date); }

    public Date getDateTime_end() { return DateConverter.toDate(date_end); }
    public void setDateTime_end(Date date) { this.date_end = DateConverter.toTimestamp(date); }
    //endregion

    public String getCaller_name() { return caller_name; }
    public void setCaller_name(String caller_name) { this.caller_name = caller_name; }

    public int getCall_type() { return call_type; }
    public void setCall_type(int call_type) { this.call_type = call_type; }

    public String getPath() { return path; }
    public void setPath(String path) {
        this.path = path;
        this.file = new File(path);
    }

    public File getFile() { return file; }
    public void setFile(File file) {
        this.file = file;
        this.path = file.getAbsolutePath();
    }

    //endregion

    //region CLONEABLE

    public AModel Clone() { return new FilesRecorder(this); }

    public void CopyFrom(AModel model) {
        FilesRecorder file = (FilesRecorder) model;
        this.path = file.getPath();
        this.caller_name = file.getCaller_name();
        this.date_start = file.getDate_start();
        this.date_end = file.getDate_end();
        this.call_type = file.getCall_type();
    }

    //endregion

}
