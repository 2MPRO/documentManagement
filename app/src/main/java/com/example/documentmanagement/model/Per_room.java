package com.example.documentmanagement.model;

public class Per_room {
    String  idPer_room;
    String  idPer;
    String  idRoom;

    public Per_room(String idPer_room, String idPer, String idRoom) {
        this.idPer_room = idPer_room;
        this.idPer = idPer;
        this.idRoom = idRoom;
    }

    public String getIdPer_room() {
        return idPer_room;
    }

    public void setIdPer_room(String idPer_room) {
        this.idPer_room = idPer_room;
    }

    public String getIdPer() {
        return idPer;
    }

    public void setIdPer(String idPer) {
        this.idPer = idPer;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }
}
