package com.example.quanlyphucvunhahang.models.modelsEntity;

import com.example.quanlyphucvunhahang.models.modelsEntity.GiaMonAn;
import com.example.quanlyphucvunhahang.models.modelsEntity.DanhGiaMonAn;
import com.example.quanlyphucvunhahang.models.modelsEntity.NhomMonAnEntity;
import java.util.ArrayList;
import java.util.List;

public class MonAnEntity {
    private String ID;
    private List<DanhGiaMonAn> listDanhGia;
    private List<GiaMonAn> listGia;
    private String tenMonAn;
    private String moTa;
    private KhuyenMaiEntity khuyenMai;
    private NhomMonAnEntity nhomMonAn;

    public MonAnEntity() {}
    public MonAnEntity(String ID, List<DanhGiaMonAn> listDanhGia, List<GiaMonAn> listGia,
                       String tenMonAn, String moTa, KhuyenMaiEntity khuyenMai,
                       NhomMonAnEntity nhomMonAn ) {
        this.ID = ID;
        this.listDanhGia = listDanhGia;
        this.listGia = listGia;
        this.tenMonAn = tenMonAn;
        this.moTa = moTa;
        this.khuyenMai = khuyenMai;
        this.nhomMonAn = nhomMonAn;
    }

    public String getID() {
        return ID;
    }

    public List<DanhGiaMonAn> getListDanhGia() {
        return listDanhGia;
    }

    public List<GiaMonAn> getListGia() {
        return listGia;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public String getMoTa() {
        return moTa;
    }

    public KhuyenMaiEntity getKhuyenMai() {
        return khuyenMai;
    }

    public NhomMonAnEntity getNhomMonAn() {
        return nhomMonAn;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public void setListDanhGia(List<DanhGiaMonAn> listDanhGia) {
        this.listDanhGia = listDanhGia;
    }

    public void setListGia(List<GiaMonAn> listGia) {
        this.listGia = listGia;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setKhuyenMai(KhuyenMaiEntity khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setNhomMonAn(NhomMonAnEntity nhomMonAn) {
        this.nhomMonAn = nhomMonAn;
    }

}
