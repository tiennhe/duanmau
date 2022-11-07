package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.databasse.DbHelper;
import com.example.myapplication.model.Loai_Sach;

import java.util.ArrayList;

public class Loai_Sach_Dao {
    DbHelper helper ;
    public Loai_Sach_Dao(Context context){
        helper = new DbHelper(context);

    }
    public ArrayList<Loai_Sach> getAllLoaiSach(){
        ArrayList <Loai_Sach> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from loaisach"  , null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                list.add(new Loai_Sach(cursor.getInt(0) , cursor.getString(1)));
                cursor.moveToNext();
            }
        }
        return list;
    }
    public boolean themLoaiSach(String tenloai){
        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tenloai" ,  tenloai );
        long check = database.insert("loaisach" , null , values);
        if(check==-1){
            return false;
        }else {
            return true;
        }
    }
}
