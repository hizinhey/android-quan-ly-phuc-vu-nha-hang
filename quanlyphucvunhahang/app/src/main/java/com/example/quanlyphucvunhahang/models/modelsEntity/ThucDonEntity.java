package com.example.quanlyphucvunhahang.models.modelsEntity;

import java.util.List;

public class ThucDonEntity {
    private BuaAnEntity buaAn;
    private List<ChiTietThucDon> chiTiet;
    private TaiKhoanEntity khachHang;
    private long tongTien;

    public ThucDonEntity(BuaAnEntity buaAn, List<ChiTietThucDon> chiTiet, TaiKhoanEntity khachHang, long tongTien) {
        this.buaAn = buaAn;
        this.chiTiet = chiTiet;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
    }

    public ThucDonEntity() {}

    public BuaAnEntity getBuaAn() {
        return buaAn;
    }

    public void setBuaAn(BuaAnEntity buaAn) {
        this.buaAn = buaAn;
    }

    public List<ChiTietThucDon> getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(List<ChiTietThucDon> chiTiet) {
        this.chiTiet = chiTiet;
    }

    public TaiKhoanEntity getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(TaiKhoanEntity khachHang) {
        this.khachHang = khachHang;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }
}
