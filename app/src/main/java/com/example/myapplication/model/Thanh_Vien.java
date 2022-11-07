package com.example.myapplication.model;

public class Thanh_Vien {
    private int mathanhvien;
    private String hotenthanhvien;
    private  String namsinh;

    public String getHotenthanhvien() {
        return hotenthanhvien;
    }

    public void setHotenthanhvien(String hotenthanhvien) {
        this.hotenthanhvien = hotenthanhvien;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public int getMathanhvien() {
        return mathanhvien;
    }

    public void setMathanhvien(int mathanhvien) {
        this.mathanhvien = mathanhvien;
    }

    public Thanh_Vien() {
    }

    public Thanh_Vien(int mathanhvien, String hotenthanhvien, String namsinh) {
        this.mathanhvien = mathanhvien;
        this.hotenthanhvien = hotenthanhvien;
        this.namsinh = namsinh;
    }
}
