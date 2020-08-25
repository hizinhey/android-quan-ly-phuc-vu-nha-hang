package com.example.managerapp.models.modelsEntity;

public class NhomMonAnEntity {
    private String ID;
    private String tenNhom;

    public NhomMonAnEntity() {}

    public NhomMonAnEntity(String ID, String tenNhom) {
        this.ID = ID;
        this.tenNhom = tenNhom;
    }

    public String getID() {
        return ID;
    }

    public String getTenNhom() {
        return tenNhom;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }
}
