package com.example.quanlyphucvunhahang.models.modelsEntity;

import java.util.List;

public class LichSuEntity {
    private List<ChiTietLichSu> chiTiet;
    private TaiKhoanEntity khachHang;
    private float tongTien;

    public LichSuEntity(){}

    public LichSuEntity(List<ChiTietLichSu> chiTiet, TaiKhoanEntity khachHang, float tongTien) {
        this.chiTiet = chiTiet;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
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

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}
