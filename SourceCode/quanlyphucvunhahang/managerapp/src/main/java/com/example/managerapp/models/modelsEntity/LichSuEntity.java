package com.example.managerapp.models.modelsEntity;

import java.util.List;

public class LichSuEntity {
    private List<ChiTietLichSu> chiTiet;
    private TaiKhoanEntity khachHang;

    public LichSuEntity(){}

    public LichSuEntity(List<ChiTietLichSu> chiTiet, TaiKhoanEntity khachHang) {
        this.chiTiet = chiTiet;
        this.khachHang = khachHang;
    }

    public List<ChiTietLichSu> getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(List<ChiTietLichSu> chiTiet) {
        this.chiTiet = chiTiet;
    }

    public TaiKhoanEntity getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(TaiKhoanEntity khachHang) {
        this.khachHang = khachHang;
    }
}
