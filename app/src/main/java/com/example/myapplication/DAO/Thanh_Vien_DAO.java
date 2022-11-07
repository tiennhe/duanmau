package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.databasse.DbHelper;
import com.example.myapplication.model.Thanh_Vien;

import java.util.ArrayList;

public class Thanh_Vien_DAO {
    DbHelper helper ;
    public Thanh_Vien_DAO(Context context){
        helper = new DbHelper(context);
    }
    public ArrayList<Thanh_Vien> getAllThanhVien(){
        ArrayList<Thanh_Vien> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from thanhvien" , null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                list.add(new Thanh_Vien(cursor.getInt(0) , cursor.getString(1) , cursor.getString(2)));
                cursor.moveToNext();
            }
        }
        return list;

    }
    public boolean themthanhvien(String hoten , String namsinh){
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hotenthanhvien" , hoten);
        values.put("namsinh" , namsinh);
        long check = database.insert("thanhvien" , null , values);
        if(check==-1){
            return false;

        }else {
            return true;
        }
    }
    public boolean capnhap(int matv ,String hoten , String namsinh){
            SQLiteDatabase database = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
        values.put("hotenthanhvien" , hoten);
        values.put("namsinh" , namsinh);
        long check = database.update("thanhvien" , values , "MaTV = ?" , new String[]{String.valueOf(matv)});
        if(check==-1)
            return false;
        return true;
    }
    public  int  xoathongtin(int matv){
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM phieumuon where MaTV = ?"  , new String[]{String.valueOf(matv)});
        if(cursor.getCount()!=0){
            return  -1 ;

        }
        long check = database.delete("thanhvien" , "MaTV = ?" , new String[]{String.valueOf(matv)});
        if(check ==-1)
            return 0;
        return 1;
    }
}
