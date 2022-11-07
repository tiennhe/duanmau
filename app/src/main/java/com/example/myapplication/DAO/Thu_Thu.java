package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.databasse.DbHelper;

public class Thu_Thu {
    DbHelper helper;

    public Thu_Thu(Context context) {
        helper = new DbHelper(context);

    }

    public boolean checkdangnhap(String MaTT, String matkhau) {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM thuthu where MaTT = ? and matkhau = ? ", new String[]{MaTT, matkhau});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean capnhapmatkhau(String usermame, String oldpass, String newpass) {
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from thuthu where MaTT = ? and matkhau = ?", new String[]{usermame, oldpass});
        if (cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("matkhau", newpass);
            long check = database.update("thuthu", values, "MaTT = ?", new String[]{usermame});
            if (check == -1)
                return false;
            return true;
        }
        return false;
    }
}

