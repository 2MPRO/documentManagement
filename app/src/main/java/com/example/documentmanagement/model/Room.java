package com.example.documentmanagement.model;

public class Room {
    String idRoom;
    String roomName;

    public Room(String idRoom, String roomName) {
        this.idRoom = idRoom;
        this.roomName = roomName;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
