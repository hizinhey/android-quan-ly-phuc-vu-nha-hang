package com.example.managerapp.models.modelsEntity;

import java.util.Date;
public class GiaMonAn {
    private int gia;
    private Date ngayTao;

    public GiaMonAn(){}

    public GiaMonAn(int gia, Date ngayTao){
        this.gia = gia;
        this.ngayTao = ngayTao;
    }

    public int getGia() {
        return gia;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
