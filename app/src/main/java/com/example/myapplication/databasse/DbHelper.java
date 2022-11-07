package com.example.myapplication.databasse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper( Context context) {
        super(context, "DB_HELPER", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_thu_thu = "Create table thuthu(MaTT text primary key , hotenthuthu text , matkhau text )";
        db.execSQL(table_thu_thu);

        String table_thanh_vien = "Create table thanhvien (MaTV integer primary key  autoincrement, hotenthanhvien text , namsinh text )";
        db.execSQL(table_thanh_vien);

        String table_loai_sach = "Create table loaisach(MaLS integer primary key autoincrement , tenloai text )";
        db.execSQL(table_loai_sach);

        String table_sach = "Create table sach(MaS integer primary key autoincrement , tensach text , giathue integer , MaLS integer references loaisach(MaLS))";
        db.execSQL(table_sach);

        String table_phieu_muon = "Create table phieumuon(MaPM integer primary key  autoincrement , MaTT Text references thuthu(MaTT) ," +
                " MaTV integer references thanhvien(MaTV) , " +
                "MaS references sach(MaS) , ngay DATE , trasach integer , tienthue integer) ";
        db.execSQL(table_phieu_muon);

        //data base
        db.execSQL("Insert into  loaisach values (1, 'thiếu nhi') , (2 , 'cong nghệ thông tin') , (3 , 'khoa học ') , (4, 'kĩ thuật')");
        db.execSQL("insert into sach values (1 , 'java' , 2000 , 2) , (2 , 'ô tô' , 3000 , 4) , (3 , 'doremon' , 3000 , 1)");
        db.execSQL("insert into thuthu values ('thuthu01', 'Nguyễn Văn A' , 'abc123') , ('thuthu02' , 'Nguyễn văn b' , '1234')");
        db.execSQL("insert into  thanhvien values (1 , 'Quán Hoàn Tiến' , '2003') , (2 , 'Trần Quốc Đạt' , '2003')");
        db.execSQL("insert into phieumuon values (1 , 'thuthu01' , 1 ,1 , '04/10/2022' ,1 , 2000) , (2 , 'thuthu01' , 2 , 3 , '04/10/2022' , 0 , 3000)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("drop table if exists  thuthu");
            db.execSQL("drop table if exists  thanhvien");
            db.execSQL("drop table if exists  loaisach");
            db.execSQL("drop table if exists  sach");
            db.execSQL("drop table if exists  phieumuon");
            onCreate(db);
        }
    }
}
