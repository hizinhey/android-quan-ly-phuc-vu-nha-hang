package com.example.quanlyphucvunhahang.models.modelsEntity;

import java.util.Date;

public class KhuyenMaiEntity{

    // Constructor
    public KhuyenMaiEntity(){};
    public KhuyenMaiEntity(String ID, String strTen, String strMoTa, Date dateBatDau, Date dateKetThuc, String strPhuongThuc) {
        this.ID = ID;
        this.strTen = strTen;
        this.strMoTa = strMoTa;
        DateBatDau = dateBatDau;
        DateKetThuc = dateKetThuc;
        this.strPhuongThuc = strPhuongThuc;
    }

    // Function
    /* Thuong Entity khong can function*/

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

    // Biến
    private String ID;
    private String strTen;
    private String strMoTa;
    private Date DateBatDau;
    private Date DateKetThuc;
    private String strPhuongThuc;
}
