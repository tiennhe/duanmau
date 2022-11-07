package com.example.myapplication.model;

public class Phieu_Muon_MODEL {
    private int maphieumuon;
    private String mathuthu;
    private int mathanhvien;
    private int masach;
    private String ngay;
    private int trasach;
    private int tienthue;
    private  String tentv;
    private String tentt;
    private  String tensach;


    public int getMaphieumuon() {
        return maphieumuon;
    }

    public void setMaphieumuon(int maphieumuon) {
        this.maphieumuon = maphieumuon;
    }

    public String getMathuthu() {
        return mathuthu;
    }

    public void setMathuthu(String mathuthu) {
        this.mathuthu = mathuthu;
    }

    public int getMathanhvien() {
        return mathanhvien;
    }

    public void setMathanhvien(int mathanhvien) {
        this.mathanhvien = mathanhvien;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public Phieu_Muon_MODEL() {
    }

    public Phieu_Muon_MODEL(int maphieumuon, String mathuthu, int mathanhvien, int masach, String ngay, int trasach, int tienthue, String tentv, String tentt, String tensach) {
        this.maphieumuon = maphieumuon;
        this.mathuthu = mathuthu;
        this.mathanhvien = mathanhvien;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
        this.tentv = tentv;
        this.tentt = tentt;
        this.tensach = tensach;
    }

    public Phieu_Muon_MODEL(int maphieumuon, int mathanhvien, int masach, String ngay) {
        this.maphieumuon = maphieumuon;
        this.mathanhvien = mathanhvien;
        this.masach = masach;
        this.ngay = ngay;
    }

    public Phieu_Muon_MODEL(String mathuthu, int mathanhvien, int masach, String ngay, int trasach, int tienthue) {

        this.mathuthu = mathuthu;
        this.mathanhvien = mathanhvien;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
    }
}