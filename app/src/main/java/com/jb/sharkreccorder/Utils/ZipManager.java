package com.jb.sharkreccorder.Utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import android.util.Log;


public class ZipManager {

    private static int BUFFER_SIZE = 6 * 1024;

    // HOW TO USE
    //String backupDBPath = Environment.getExternalStorageDirectory().getPath() + "/Mobikul-Pos-db-backup";
    //final File backupDBFolder = new File(backupDBPath);
    //        backupDBFolder.mkdirs();
    //        final File backupDB = new File(backupDBFolder, "/db_pos.db");
    //String[] s = new String[1];
    //s[0] = backupDB.getAbsolutePath();
    //zip(s, backupDBPath + "/pos_demo.zip");

    public static void zip(String[] files, String zipFile) throws IOException {
        BufferedInputStream origin = null;

        try (ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile))))
        {
            byte data[] = new byte[BUFFER_SIZE];

            for (String file : files) {
                FileInputStream fi = new FileInputStream(file);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                try
                {
                    ZipEntry entry = new ZipEntry(file.substring(file.lastIndexOf("/") + 1));
                    out.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1)
                    {
                        out.write(data, 0, count);
                    }
                }
                finally
                {
                    origin.close();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("ZipManager", "Zip exception", e);
        }
    }


    // HOW TO USE
    // File sd = Environment.getExternalStorageDirectory();
    //if (sd.canWrite()) {
    //   final File backupDBFolder = new File(sd.getPath());
    //   unzip(backupDBPath, backupDBFolder.getPath());
    //}
    public static void unzip(String zipFile, String location) throws IOException {
        try {
            File f = new File(location);
            if (!f.isDirectory())
            {
                f.mkdirs();
            }

            try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile)))
            {
                ZipEntry ze = null;
                while ((ze = zin.getNextEntry()) != null) {
                    String path = location + File.separator + ze.getName();

                    if (ze.isDirectory())
                    {
                        File unzipFile = new File(path);
                        if (!unzipFile.isDirectory())
                        {
                            unzipFile.mkdirs();
                        }
                    }
                    else
                    {
                        try (FileOutputStream fout = new FileOutputStream(path, false))
                        {
                            for (int c = zin.read(); c != -1; c = zin.read())
                            {
                                fout.write(c);
                            }
                            zin.closeEntry();
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("ZipManager", "Unzip exception", e);
        }
    }

}