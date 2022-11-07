package com.example.myapplication.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.databasse.DbHelper;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class Thong_Ke_Dao {
    DbHelper helper ;
    public Thong_Ke_Dao(Context context) {
        helper = new DbHelper(context);

    }
    public ArrayList<Sach> gettop10(){
        ArrayList <Sach> list = new ArrayList<>();
        SQLiteDatabase  database =helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT pm.MaS , sc.tensach , COUNT(pm.MaS)\n" +
                "FROM phieumuon pm  , sach sc\n" +
                "where pm.MaS = sc.MaS\n" +
                "GROUP BY pm.MaS  , sc.tensach\n" +
                "ORDER BY COUNT (pm.MaS) DESC\n" +
                "LIMIT 10" , null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                list.add(new Sach(cursor.getInt(0) , cursor.getString(1) , cursor.getInt(2)));
                cursor.moveToNext();
            }
        }
        return list;
    }
    public int getdoanhthu(String ngaybatdau , String ngayketthu){
        ngaybatdau = ngaybatdau.replace("/" , "");
        ngayketthu = ngayketthu.replace("/" , "");
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT SUM(tienthue)\n" +
                "FROM phieumuon\n" +
                "WHERE substr(ngay,7)|| substr(ngay,4,2)||substr(ngay,1,2) BETWEEN ? AND ? " ,new String[]{ngaybatdau , ngayketthu} );
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
