package com.example.myapplication.model;

public class Loai_Sach {
    private int maloaisach;
    private  String tenloaisach;

    public int getMaloaisach() {
        return maloaisach;
    }

    public void setMaloaisach(int maloaisach) {
        this.maloaisach = maloaisach;
    }

    public String getTenloaisach() {
        return tenloaisach;
    }

    public void setTenloaisach(String tenloaisach) {
        this.tenloaisach = tenloaisach;
    }

    public Loai_Sach() {
    }

    public Loai_Sach(int maloaisach, String tenloaisach) {
        this.maloaisach = maloaisach;
        this.tenloaisach = tenloaisach;
    }
}
