package com.example.quanlyphucvunhahang.models.modelsEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.TaiKhoanEntity;

public class QuanLyEntity {
    String ID;
    TaiKhoanEntity taiKhoan;
    private String hoTen;
    private String sdt;
    private String email;

    public QuanLyEntity(){}

    public QuanLyEntity(String ID, TaiKhoanEntity taiKhoan, String hoTen, String sdt, String email){
        this.ID = ID;
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public TaiKhoanEntity getTaiKhoan() {
        return taiKhoan;
    }

    public String getEmail() {
        return email;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTaiKhoan(TaiKhoanEntity taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
