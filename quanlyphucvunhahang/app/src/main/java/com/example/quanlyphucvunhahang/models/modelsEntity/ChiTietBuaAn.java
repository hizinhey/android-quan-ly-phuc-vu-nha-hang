package com.example.quanlyphucvunhahang.models.modelsEntity;
import com.example.quanlyphucvunhahang.models.modelsEntity.MonAnEntity;

public class ChiTietBuaAn {
    private MonAnEntity monAn;
    private int trangThai;

    public ChiTietBuaAn(){}
    public ChiTietBuaAn(MonAnEntity monAn, int trangThai){
        this.monAn = monAn;
        this.trangThai = trangThai;
    }

    public MonAnEntity getMonAn() {
        return monAn;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMonAn(MonAnEntity monAn) {
        this.monAn = monAn;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String strTrangThai() {
        if (trangThai==0)
            return ("Dang chuan bi");
        return ("Hoan thanh");
    }
}
