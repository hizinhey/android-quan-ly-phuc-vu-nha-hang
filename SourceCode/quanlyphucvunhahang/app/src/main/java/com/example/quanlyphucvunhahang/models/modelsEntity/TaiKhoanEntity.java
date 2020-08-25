package com.example.quanlyphucvunhahang.models.modelsEntity;

public class TaiKhoanEntity {
    //Thuoc tinh
    private String ID;
    private String ten;
    private String email;
    private String diachi;
    private String sdt;
    private int phanQuyen;

    public TaiKhoanEntity() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(int phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public TaiKhoanEntity(String ID,  String ten, String email, String diachi, String sdt, int phanQuyen) {
        this.ID = ID;
        this.ten = ten;
        this.email = email;
        this.diachi = diachi;
        this.sdt = sdt;
        this.phanQuyen = phanQuyen;
    }
}
