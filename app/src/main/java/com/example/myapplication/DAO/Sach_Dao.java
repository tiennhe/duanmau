package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.databasse.DbHelper;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class Sach_Dao {
    DbHelper helper;
    public Sach_Dao (Context context){
        helper = new DbHelper(context);

    }
    public ArrayList<Sach> getAllsach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select sc.MaS , sc.tensach ,sc.giathue , sc.MaLS , l.tenloai from sach sc , loaisach l where sc.MaLS = l.MaLS" , null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(new Sach(cursor.getInt(0) , cursor.getString(1) , cursor.getInt(2) , cursor.getInt(3) , cursor.getString(4)));
                cursor.moveToNext();
            }
        }
        return list;
    }
    public boolean themsach(String tensach , int tienthue , int maloai){
        SQLiteDatabase database =helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensach" , tensach);
        values.put("giathue" , tienthue);
        values.put("MaLS" , maloai);
        long chek = database.insert("sach" , null , values);
        if(chek==-1)
            return false;
        return true;
    }
    public  boolean capnhapsach(int masach,String tensach , int tienthue , int maloai){
        SQLiteDatabase database =helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensach" , tensach);
        values.put("giathue" , tienthue);
        values.put("MaLS" , maloai);
        long check = database.update("sach" , values , "MaS = ?" , new String[]{String.valueOf(masach)});
        if(check==-1)
            return false;
        return true;
    }
    public  int xoasach(int masach){
        SQLiteDatabase database =helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM phieumuon where  MaS = ?" , new String[]{String.valueOf(masach)});
        if(cursor.getCount()!=0){
            return -1;
        }
        long check = database.delete("sach" , "MaS = ?" , new String[]{String.valueOf(masach)});
        if(check==-1)
            return 0;
        return 1;
    }
    public ArrayList<Sach> lietke(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase database =  helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT sach.MaS , tensach , giathue , loaisach.MaLS \n" +
                "FROM sach INNER JOIN loaisach \n" +
                "on sach.MaLS = loaisach.MaLS\n" +
                "where tenloai LIKE'IT%'" , null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                list.add(new Sach(cursor.getInt(0) , cursor.getString(1) ,
                        cursor.getInt(2) , cursor.getInt(3)));
                cursor.moveToNext();
            }
        }
        return list;
    }

}
