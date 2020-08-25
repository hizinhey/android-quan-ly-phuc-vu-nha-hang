package com.example.managerapp.models.modelsEntity;

import java.util.Date;

public class DanhGiaMonAn {
    private TaiKhoanEntity khachHang;
    private String noiDung;
    private Date ngayTao;

    public DanhGiaMonAn(){}
    public DanhGiaMonAn(TaiKhoanEntity khachHang, String noiDung, Date ngayTao){
        this.khachHang = khachHang;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
    }

    public TaiKhoanEntity getKhachHang() {
        return khachHang;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setKhachHang(TaiKhoanEntity khachHang) {
        this.khachHang = khachHang;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
