package com.jb.sharkreccorder.Utils.Converters;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
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

    public static String Duration(Date date_start, Date date_end) {
        Duration time = Duration.between(date_start.toInstant(), date_end.toInstant());
        int seconde = Math.round(time.getSeconds() % 60);
        int minutes = Math.round(time.toMinutes());
        return minutes + " : " + seconde;
    }


}