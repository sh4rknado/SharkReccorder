package com.jb.sharkreccorder.Utils.Converters;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(String stringdate){
        return stringdate == null ? null : new Date(stringdate);
    }

    @TypeConverter
    public static String toTimestamp(Date date){
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return dateTimeFormat.format(date);
    }

    public static String GetDateNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(new Date());
    }

}