package com.jb.sharkreccorder.Utils.Permissions;

public class PermissionItem {
    private boolean permissionGranted;
    private String name;


    public PermissionItem(boolean _perm, String _name) {
        setPermissionGranted(_perm);
        setName(_name);
    }


    public boolean isPermissionGranted() { return permissionGranted; }
    public void setPermissionGranted(boolean permissionGranted) { this.permissionGranted = permissionGranted; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
