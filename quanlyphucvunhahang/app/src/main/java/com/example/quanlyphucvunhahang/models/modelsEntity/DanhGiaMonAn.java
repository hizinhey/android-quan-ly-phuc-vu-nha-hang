package com.example.quanlyphucvunhahang.models.modelsEntity;

import java.util.Date;

public class DanhGiaMonAn {
    private KhachHangEntity khachHang;
    private String noiDung;
    private Date ngayTao;

    public DanhGiaMonAn(){}
    public DanhGiaMonAn(KhachHangEntity khachHang, String noiDung, Date ngayTao){
        this.khachHang = khachHang;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
    }

    public KhachHangEntity getKhachHang() {
        return khachHang;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setKhachHang(KhachHangEntity khachHang) {
        this.khachHang = khachHang;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
