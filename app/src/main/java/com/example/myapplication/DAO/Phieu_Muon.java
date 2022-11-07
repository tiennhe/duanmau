package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.databasse.DbHelper;
import com.example.myapplication.model.Phieu_Muon_MODEL;

import java.util.ArrayList;

public class Phieu_Muon {
    DbHelper helper ;
    public  Phieu_Muon(Context context){
        helper = new DbHelper(context);
    }
    public ArrayList<Phieu_Muon_MODEL> getAll(){
        ArrayList <Phieu_Muon_MODEL> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT pm.mapm,pm.matt, pm.matv,pm.MaS,  pm.ngay,pm.trasach, pm.tienthue,tv.hotenthanhvien,  tt.hotenthuthu, sc.tensach\n" +
                "FROM PHIEUMUON pm, THANHVIEN tv, THUTHU tt, SACH sc \n" +
                "WHERE pm.matv = tv.matv and pm.matt = tt.matt AND pm.MaS = sc.mas" , null);
        //
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                list.add(new Phieu_Muon_MODEL(cursor.getInt(0) , cursor.getString(1) , cursor.getInt(2) , cursor.getInt(3) , cursor.getString(4) , cursor.getInt(5) ,
                        cursor.getInt(6) , cursor.getString(7) , cursor.getString(8) , cursor.getString(9)));
                cursor.moveToNext();
            }
        }
        return list;
    }
    public boolean thanhDoitrangthai(int mapm){
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues   values = new ContentValues();
        values.put("trasach" , 1);
        long check = database.update("phieumuon" ,values , "MaPM = ?" , new String[]{String.valueOf(mapm)});
        if(check ==-1){
            return false;
        }
        else
        {
            return  true;
        }
    }
    public boolean themphieumuon (Phieu_Muon_MODEL phieumuon){
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //MaPM integer primary key  autoincrement , MaTT Text references thuthu(MaTT) ," +
        //                " MaTV integer references thanhvien(MaTV) , " +
        //                "MaS references sach(MaS) , ngay text , trasach integer , tienthue integer) ";

        values.put("MaTT" , phieumuon.getMathuthu());
        values.put("MaTV" , phieumuon.getMathanhvien());
        values.put("MaS" , phieumuon.getMasach());
        values.put("ngay" , phieumuon.getNgay());
        values.put("trasach" , phieumuon.getTrasach());
        values.put("tienthue" , phieumuon.getTienthue());

        long check = database.insert("phieumuon" , null , values);
        if(check ==-1){
            return false;
        }else {
            return true;
        }




    }
    public  ArrayList<Phieu_Muon_MODEL> getlietke(){
        ArrayList<Phieu_Muon_MODEL> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
    //int maphieumuon, int mathanhvien, int masach, String ngay
        Cursor cursor = database.rawQuery("SELECT phieumuon.MaPM , thanhvien.MaTV , sach.MaS , ngay\n" +
                "from phieumuon INNER JOIN thanhvien on phieumuon.matv = thanhvien.MaTV\n" +
                "INNER join sach on sach.MaS = phieumuon.mas\n" +
                "WHERe ngay  = '04/10/2022'" , null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                list.add(new Phieu_Muon_MODEL(cursor.getInt(0) , cursor.getInt(1) , cursor.getInt(2) , cursor.getString(3)));
                cursor.moveToNext();
            }
        }
        return list;
    }
    public  ArrayList<Phieu_Muon_MODEL> gettrongkhoang(){
        ArrayList<Phieu_Muon_MODEL> list = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        //int maphieumuon, int mathanhvien, int masach, String ngay
        Cursor cursor = database.rawQuery("SELECT phieumuon.MaPM , thanhvien.MaTV , sach.MaS , ngay\n" +
                "from phieumuon INNER JOIN thanhvien on phieumuon.matv = thanhvien.MaTV\n" +
                "INNER join sach on sach.MaS = phieumuon.mas\n" +
                "WHERe ngay BETWEEN '04/10/2022' AND '23/10/2022' " , null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                list.add(new Phieu_Muon_MODEL(cursor.getInt(0) , cursor.getInt(1) , cursor.getInt(2) , cursor.getString(3)));
                cursor.moveToNext();
            }
        }
        return list;
    }

}
