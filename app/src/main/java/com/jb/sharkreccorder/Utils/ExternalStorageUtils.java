package com.jb.sharkreccorder.Utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import androidx.annotation.RequiresApi;

import java.io.File;

public class ExternalStorageUtils {

    // Check whether the external storage is mounted or not.
    public static boolean isExternalStorageMounted() {
        String dirState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(dirState);
    }

    // Check whether the external storage is read only or not.
    public static boolean isExternalStorageReadOnly() {

        String dirState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(dirState);
    }

    // Get private external storage base directory.
    public static String getPrivateExternalStorageBaseDir(Context context, String dirType) {
        if(isExternalStorageMounted()) {
            return context.getExternalFilesDir(dirType).getAbsolutePath();
        }
        return "";
    }

    // Get private cache external storage base directory.
    public static String getPrivateCacheExternalStorageBaseDir(Context context) {
        if(isExternalStorageMounted()) {
            return context.getExternalCacheDir().getAbsolutePath();
        }
        return "";
    }

    // Get public external storage base directory.
    public static String getPublicExternalStorageBaseDir() {
        if(isExternalStorageMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "";
    }

    public static String getPublicExternalStorageBaseDir(Context context) {
        if(isExternalStorageMounted()) {
            return context.getExternalFilesDir(null).getAbsolutePath();
        }
        return "";
    }

    // Get public external storage base directory.
    public static File tryGetPublicExternalStorageBaseDir(Context context) throws Exception {
        String path = getPublicExternalStorageBaseDir(context);
        File file = new File(path, "SharkRecorder");

        if(!file.exists() && !file.mkdirs())
            throw new Exception("Cannot create file : " + file.getAbsolutePath());
        else
            return file;
    }

    public static String getExternalStorageBaseDir(String dirType, Context context) {
        if(isExternalStorageMounted()) {
            return context.getExternalFilesDir(dirType).getAbsolutePath();
        }
        return "";
    }

    // Get external storage disk space, return MB
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getExternalStorageSpace() {
        long ret = 0;
        if (isExternalStorageMounted()) {
            StatFs fileState = new StatFs(getPublicExternalStorageBaseDir());

            // Get total block count.
            long count = fileState.getBlockCountLong();

            // Get each block size.
            long size = fileState.getBlockSizeLong();

            // Calculate total space size
            ret = count * size / 1024 / 1024;
        }
        return ret;
    }

    // Get external storage left free disk space, return MB
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getExternalStorageLeftSpace() {
        long ret = 0;
        if (isExternalStorageMounted()) {
            StatFs fileState = new StatFs(getPublicExternalStorageBaseDir());

            // Get free block count.
            long count = fileState.getFreeBlocksLong();

            // Get each block size.
            long size = fileState.getBlockSizeLong();

            // Calculate free space size
            ret = count * size / 1024 / 1024;
        }
        return ret;
    }

    // Get external storage available disk space, return MB
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getExternalStorageAvailableSpace() {
        long ret = 0;
        if (isExternalStorageMounted()) {
            StatFs fileState = new StatFs(getPublicExternalStorageBaseDir());

            // Get available block count.
            long count = fileState.getAvailableBlocksLong();

            // Get each block size.
            long size = fileState.getBlockSizeLong();

            // Calculate available space size
            ret = count * size / 1024 / 1024;
        }
        return ret;
    }
}
