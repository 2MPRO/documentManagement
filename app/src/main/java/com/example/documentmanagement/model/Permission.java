package com.example.documentmanagement.model;

public class Permission {
    private String idPer_room;
    private String idPermission;
    private String permissionName;

    public Permission(String idPer_room, String idPermission, String permissionName) {
        this.idPer_room = idPer_room;
        this.idPermission = idPermission;
        this.permissionName = permissionName;
    }

    public Permission(String idPermission, String permissionName) {
        this.idPermission = idPermission;
        this.permissionName = permissionName;
    }

    public String getIdPer_room() {
        return idPer_room;
    }

    public void setIdPer_room(String idPer_room) {
        this.idPer_room = idPer_room;
    }

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
}
