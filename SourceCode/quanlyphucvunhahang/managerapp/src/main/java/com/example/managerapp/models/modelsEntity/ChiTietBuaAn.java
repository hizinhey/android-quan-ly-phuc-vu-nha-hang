package com.example.managerapp.models.modelsEntity;
import com.example.managerapp.models.modelsEntity.MonAnEntity;

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
            return ("Đang chuẩn bị");
        return ("Hoàn thành");
    }

    public String colorTrangThai(){
        if (trangThai==0)
            return ("#6c757d");
        return ("#2b9348");
    }
}
