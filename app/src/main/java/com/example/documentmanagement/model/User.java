package com.example.documentmanagement.model;

public class User {
    private String idUser;
    private String fullName;
    private String pass;
    private String idRoom;
    private String roomName;
    private String birthDay;
    private String sex;
    private String diaChi;

    public User(String idUser, String fullName, String pass, String idRoom, String roomName, String birthDay, String sex, String diaChi) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.pass = pass;
        this.idRoom = idRoom;
        this.roomName = roomName;
        this.birthDay = birthDay;
        this.sex = sex;
        this.diaChi = diaChi;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
