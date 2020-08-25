package com.example.quanlyphucvunhahang.models.modelsEntity;

import java.util.Date;
import java.util.List;

public class BuaAnEntity {
    private String ID;
    private String banAn;
    private List<ChiTietBuaAn> listChiTietBuaAn;
    private int hinhThucThanhToan;
    private TaiKhoanEntity khachHang;
    private String nhanXet;
    private int trangThai;

    public BuaAnEntity(){}
    public BuaAnEntity(String ID, String banAn, List<ChiTietBuaAn> listChiTietBuaAn,
                       int hinhThucThanhToan, TaiKhoanEntity khachHang, String nhanXet,
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

    public TaiKhoanEntity getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(TaiKhoanEntity khachHang) {
        this.khachHang = khachHang;
    }

    public String getBanAn() {
        return banAn;
    }

    public void setBanAn(String banAn) {
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
            return ("Tiền mặt");
        else
            return ("Online");
    }

    public String strTrangThai()
    {
        if (trangThai == 1)
            return ("Đang có bữa ăn");
        if (trangThai == 2)
            return ("Kết thúc bữa ăn");
        return ("Chưa có bữa ăn");
    }
}
