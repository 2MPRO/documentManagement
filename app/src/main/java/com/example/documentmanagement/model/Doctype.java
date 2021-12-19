package com.example.documentmanagement.model;

public class Doctype {
    String idType ;
    String typeName;

    public Doctype(String idType, String typeName) {
        this.idType = idType;
        this.typeName = typeName;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
