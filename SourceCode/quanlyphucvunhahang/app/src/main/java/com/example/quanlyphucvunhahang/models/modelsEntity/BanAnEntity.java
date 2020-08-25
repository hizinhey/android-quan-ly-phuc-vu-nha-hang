package com.example.quanlyphucvunhahang.models.modelsEntity;

public class BanAnEntity {
    private String id;
    private boolean isBusy;

    public BanAnEntity(){}
    public BanAnEntity(String id, boolean isBusy) {
        this.id = id;
        this.isBusy = isBusy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(boolean busy) {
        isBusy = busy;
    }
}
