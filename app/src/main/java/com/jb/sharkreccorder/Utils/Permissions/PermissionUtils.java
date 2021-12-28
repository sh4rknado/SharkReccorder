package com.jb.sharkreccorder.Utils.Permissions;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PermissionUtils {

    public static boolean CheckPermission(Context context, String permission) {
        int check = ContextCompat.checkSelfPermission(context, permission);
        return check == PackageManager.PERMISSION_GRANTED;
    }

    public static ArrayList<PermissionItem> CheckPermissions(Context context, String[] permissions) {
        ArrayList<PermissionItem> items = new ArrayList<PermissionItem>();

        for (String perm: permissions) {
            if(ContextCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_DENIED) {
                items.add(new PermissionItem(false, perm));
            }
        }

        return items;
    }

    public static void RequestPermissions(Activity activity, String[] permissions) {
        ActivityCompat.requestPermissions(activity, permissions, 1000);
    }

    public static void RequestPermissionsFromPermissionsItems(Activity activity, ArrayList<PermissionItem> permissionItems) {

        int size = permissionItems.size();

        if(size == 0)
            return;

        String[] permissions= new String[size];

        for (int i=0; i < size; i++)
            permissions[i] = permissionItems.get(i).getName();

        ActivityCompat.requestPermissions(activity, permissions, 1000);
    }

}
