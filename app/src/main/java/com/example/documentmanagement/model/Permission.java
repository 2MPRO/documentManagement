package com.example.documentmanagement.model;

public class Permission {
    private String idPermission;
    private String permissionName;

    public String getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(String idPermission) {
        this.idPermission = idPermission;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Permission(String idPermission, String permissionName) {
        this.idPermission = idPermission;
        this.permissionName = permissionName;
    }
}
