package com.example.quanlyphucvunhahang.models.modelsEntity;

import com.example.quanlyphucvunhahang.models.modelsEntity.GiaMonAn;
import com.example.quanlyphucvunhahang.models.modelsEntity.DanhGiaMonAn;
import com.example.quanlyphucvunhahang.models.modelsEntity.NhomMonAnEntity;
import java.util.ArrayList;
import java.util.List;

public class MonAnEntity {
    private String ID;
    private Float gia;
    private String tenMonAn;
    private String moTa;
    private KhuyenMaiEntity khuyenMai;
    private NhomMonAnEntity nhomMonAn;
    private String linkHinhAnh;

    public MonAnEntity() {}

    public MonAnEntity(String ID, Float gia, String tenMonAn, String moTa, KhuyenMaiEntity khuyenMai, NhomMonAnEntity nhomMonAn, String linkHinhAnh) {
        this.ID = ID;
        this.gia = gia;
        this.tenMonAn = tenMonAn;
        this.moTa = moTa;
        this.khuyenMai = khuyenMai;
        this.nhomMonAn = nhomMonAn;
        this.linkHinhAnh = linkHinhAnh;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public KhuyenMaiEntity getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMaiEntity khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public NhomMonAnEntity getNhomMonAn() {
        return nhomMonAn;
    }

    public void setNhomMonAn(NhomMonAnEntity nhomMonAn) {
        this.nhomMonAn = nhomMonAn;
    }

    public String getLinkHinhAnh() {
        return linkHinhAnh;
    }

    public void setLinkHinhAnh(String linkHinhAnh) {
        this.linkHinhAnh = linkHinhAnh;
    }
}
