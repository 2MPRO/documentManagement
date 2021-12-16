package com.example.documentmanagement.model;

public class Document {
    private String DocId;
    private String DocName;
    private String DocNum;
    private String Date;
    private String hour;
    private String docRoot;
    public Document(String docName, String docNum, String date, String hour, String docRoot) {
        this.DocName = docName;
        this.DocNum = docNum;
        this.Date = date;
        this.hour = hour;
        this.docRoot = docRoot;
    }

    public String getDocRoot() {
        return docRoot;
    }

    public void setDocRoot(String docRoot) {
        this.docRoot = docRoot;
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


}
