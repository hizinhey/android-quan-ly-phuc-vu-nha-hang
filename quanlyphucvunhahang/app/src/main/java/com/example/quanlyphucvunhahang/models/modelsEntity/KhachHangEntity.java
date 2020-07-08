package com.example.quanlyphucvunhahang.models.modelsEntity;

import com.example.quanlyphucvunhahang.models.modelsEntity.TaiKhoanEntity;

public class KhachHangEntity {
    private TaiKhoanEntity taiKhoan;
    private String hoTen;
    private String sdt;
    private String diaChi;
    private String email;

    public KhachHangEntity(){}
    public KhachHangEntity(TaiKhoanEntity taiKhoan, String hoTen, String sdt, String diaChi, String email) {
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.email = email;
    }

    public TaiKhoanEntity getTaiKhoan() {
        return taiKhoan;
    }
    public String getHoTen() {
        return hoTen;
    }
    public String getSdt() {
        return sdt;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public String getEmail() {
        return email;
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

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
