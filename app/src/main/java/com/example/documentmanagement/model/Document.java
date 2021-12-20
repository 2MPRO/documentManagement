package com.example.documentmanagement.model;

import java.io.Serializable;

public class Document implements Serializable {
    private String DocId;
    private String DocName;
    private String DocNum;
    private String Date;
    private String hour;
    private String docRoot;
    private String docRoot2;
    private String DinhKem;
    private String  loaiVanBan;
    private String  mucDo;
    private String  noiDung;


    public Document(String docId, String docName, String docNum, String date, String hour, String docRoot, String docRoot2, String dinhKem, String loaiVanBan, String mucDo, String noiDung) {
        this.DocId = docId;
        this.DocName = docName;
        this.DocNum = docNum;
        this.Date = date;
        this.hour = hour;
        this.docRoot = docRoot;
        this.docRoot2 = docRoot2;
        this.DinhKem = dinhKem;

        this.loaiVanBan = loaiVanBan;
        this.mucDo = mucDo;
        this.noiDung = noiDung;
    }

    public String getDocId() {
        return DocId;
    }

    public void setDocId(String docId) {
        this.DocId = docId;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        this.DocName = docName;
    }

    public String getDocNum() {
        return DocNum;
    }

    public void setDocNum(String docNum) {
        this.DocNum = docNum;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDocRoot() {
        return docRoot;
    }

    public void setDocRoot(String docRoot) {
        this.docRoot = docRoot;
    }

    public String getDocRoot2() {
        return docRoot2;
    }

    public void setDocRoot2(String docRoot2) {
        this.docRoot2 = docRoot2;
    }

    public String getDinhKem() {
        return DinhKem;
    }

    public void setDinhKem(String dinhKem) {
        this.DinhKem = dinhKem;
    }


    public String getLoaiVanBan() {
        return loaiVanBan;
    }

    public void setLoaiVanBan(String loaiVanBan) {
        this.loaiVanBan = loaiVanBan;
    }

    public String getMucDo() {
        return mucDo;
    }

    public void setMucDo(String mucDo) {
        this.mucDo = mucDo;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
