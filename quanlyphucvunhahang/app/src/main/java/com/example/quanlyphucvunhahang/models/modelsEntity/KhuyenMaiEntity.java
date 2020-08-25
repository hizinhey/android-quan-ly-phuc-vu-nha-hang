package com.example.quanlyphucvunhahang.models.modelsEntity;

import java.util.Date;

public class KhuyenMaiEntity{

    // Constructor
    public KhuyenMaiEntity(){
    };

    public KhuyenMaiEntity(String ID, String strTen, String strHinhAnh){
        this.ID = ID;
        this.strTen = strTen;
        this.strHinhAnh = strHinhAnh;
    }



    public KhuyenMaiEntity(String ID, String strTen, String strMoTa, Date dateBatDau, Date dateKetThuc, String strHinhAnh, String strPhuongThuc) {
        this.ID = ID;
        this.strTen = strTen;
        this.strMoTa = strMoTa;
        DateBatDau = dateBatDau;
        DateKetThuc = dateKetThuc;
        this.strHinhAnh = strHinhAnh;
        this.strPhuongThuc = strPhuongThuc;
    }

    // Getter and Setter
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStrTen() {
        return strTen;
    }

    public void setStrTen(String strTen) {
        this.strTen = strTen;
    }

    public String getStrMoTa() {
        return strMoTa;
    }

    public void setStrMoTa(String strMoTa) {
        this.strMoTa = strMoTa;
    }

    public Date getDateBatDau() {
        return DateBatDau;
    }

    public void setDateBatDau(Date dateBatDau) {
        DateBatDau = dateBatDau;
    }

    public Date getDateKetThuc() {
        return DateKetThuc;
    }

    public void setDateKetThuc(Date dateKetThuc) {
        DateKetThuc = dateKetThuc;
    }

    public String getStrPhuongThuc() {
        return strPhuongThuc;
    }

    public void setStrPhuongThuc(String strPhuongThuc) {
        this.strPhuongThuc = strPhuongThuc;
    }

    public String getStrHinhAnh() {
        return strHinhAnh;
    }

    public void setStrHinhAnh(String strHinhAnh) {
        this.strHinhAnh = strHinhAnh;
    }

    // Biến
    private String ID;
    private String strTen;
    private String strMoTa;
    private Date DateBatDau;
    private Date DateKetThuc;
    private String strHinhAnh;
    private String strPhuongThuc;
}
