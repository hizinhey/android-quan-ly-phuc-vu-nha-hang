package com.example.managerapp.models.modelsEntity;

import java.util.List;

public class ThucDonEntity {
    private BuaAnEntity buaAn;
    private TaiKhoanEntity khachHang;
    private long tongTien;

    public ThucDonEntity(BuaAnEntity buaAn, TaiKhoanEntity khachHang, long tongTien) {
        this.buaAn = buaAn;
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
