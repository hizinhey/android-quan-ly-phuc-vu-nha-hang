package com.example.quanlyphucvunhahang.models.modelsEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.KhuyenMaiEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;

public class ChiTietThucDon {
    private String ghiChu;
    private KhuyenMaiEntity khuyenMai;
    private MonAnEntity monAn;

    public ChiTietThucDon() {}

    public ChiTietThucDon(String ghiChu, KhuyenMaiEntity khuyenMai, MonAnEntity monAn) {
        this.ghiChu = ghiChu;
        this.khuyenMai = khuyenMai;
        this.monAn = monAn;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public KhuyenMaiEntity getKhuyenMai() {
        return khuyenMai;
    }

    public MonAnEntity getMonAn() {
        return monAn;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setKhuyenMai(KhuyenMaiEntity khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setMonAn(MonAnEntity monAn) {
        this.monAn = monAn;
    }
}
