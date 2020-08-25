package com.example.quanlyphucvunhahang.models.modelsEntity;

import java.util.Date;

public class ChiTietLichSu {
    private BuaAnEntity buaAn;
    private Date time;

    public ChiTietLichSu() {}

    public BuaAnEntity getBuaAn() {
        return buaAn;
    }

    public void setBuaAn(BuaAnEntity buaAn) {
        this.buaAn = buaAn;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ChiTietLichSu(BuaAnEntity buaAn, Date time) {
        this.buaAn = buaAn;
        this.time = time;
    }
}
