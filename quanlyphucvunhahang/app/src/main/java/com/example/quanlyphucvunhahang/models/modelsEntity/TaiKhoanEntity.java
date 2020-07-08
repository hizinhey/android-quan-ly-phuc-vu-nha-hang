package com.example.quanlyphucvunhahang.models.modelsEntity;

public class TaiKhoanEntity {
    //Thuoc tinh

    private String ID;
    private String user;
    private String password;
    private int phanQuyen;

    public TaiKhoanEntity(){}
    public TaiKhoanEntity(String ID, String user, String password, int phanQuyen){
        this.user = user;
        this.password = password;
        this.phanQuyen = phanQuyen;
    }

    public String getID() {
        return ID;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getPhanQuyen() {
        return phanQuyen;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhanQuyen(int phanQuyen) {
        this.phanQuyen = phanQuyen;
    }
}
