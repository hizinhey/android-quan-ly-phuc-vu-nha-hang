package com.example.quanlyphucvunhahang.models.modelsEntity;

import com.example.quanlyphucvunhahang.models.modelsEntity.ChiTietBuaAn;
import com.example.quanlyphucvunhahang.models.modelsEntity.KhachHangEntity;
import java.util.ArrayList;
import java.util.List;

public class BuaAnEntity {
    private String ID;
    private int banAn;
    private List<ChiTietBuaAn> listChiTietBuaAn;
    private int hinhThucThanhToan;
    private KhachHangEntity khachHang;
    private String nhanXet;
    private int trangThai;

    public BuaAnEntity(){}
    public BuaAnEntity(String ID, int banAn, List<ChiTietBuaAn> listChiTietBuaAn,
                       int hinhThucThanhToan, KhachHangEntity khachHang, String nhanXet,
                       int trangThai) {
        this.ID = ID;
        this.banAn = banAn;
        this.listChiTietBuaAn = listChiTietBuaAn;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.khachHang = khachHang;
        this.nhanXet = nhanXet;
        this.trangThai = trangThai;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public KhachHangEntity getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHangEntity khachHang) {
        this.khachHang = khachHang;
    }

    public int getBanAn() {
        return banAn;
    }

    public void setBanAn(int banAn) {
        this.banAn = banAn;
    }

    public int getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(int hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }


    public List<ChiTietBuaAn> getListChiTietBuaAn() {
        return listChiTietBuaAn;
    }

    public void setListChiTietBuaAn(List<ChiTietBuaAn> listChiTietBuaAn) {
        this.listChiTietBuaAn = listChiTietBuaAn;
    }

    public String getNhanXet() {
        return nhanXet;
    }

    public void setNhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
    }

    public String strHinhThucThanhToan()
    {
        if (hinhThucThanhToan == 0)
            return ("Tien Mat");
        else
            return ("Online");
    }

    public String strTrangThai()
    {
        if (trangThai == 0)
            return ("Dang An");
        return ("Hoan Tat Bua An");
    }
}
