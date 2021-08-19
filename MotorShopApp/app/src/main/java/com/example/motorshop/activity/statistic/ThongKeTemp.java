package com.example.motorshop.activity.statistic;

public class ThongKeTemp {
    String tenSP;
    String soLuong;
    String giaBan;
    String ngayDat;
    public ThongKeTemp(){}

    public ThongKeTemp(String tenSP, String soLuong, String giaBan, String ngayDat) {
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngayDat = ngayDat;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(String giaBan) {
        this.giaBan = giaBan;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    @Override
    public String toString() {
        return this.getTenSP()+","+this.getSoLuong()+","+this.getGiaBan() +","+this.getNgayDat();
    }
}
