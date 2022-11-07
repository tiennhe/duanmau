package com.example.myapplication.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.DAO.Phieu_Muon;
import com.example.myapplication.DAO.Sach_Dao;
import com.example.myapplication.DAO.Thanh_Vien_DAO;
import com.example.myapplication.R;
import com.example.myapplication.adapter.Phieu_Muon_Adapter;
import com.example.myapplication.model.Phieu_Muon_MODEL;
import com.example.myapplication.model.Sach;
import com.example.myapplication.model.Thanh_Vien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class Fragment_QLPhieuMuon extends Fragment {

    Phieu_Muon muon;
    RecyclerView recyclerView ;
    FloatingActionButton button;
    ArrayList<Phieu_Muon_MODEL> list;
    public Fragment_QLPhieuMuon() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__q_l_phieu_muon,container,false);
        //giao dien
        recyclerView = view.findViewById(R.id.recyclerview_phieumuon);
        button = view.findViewById(R.id.float_phieu_muon);
        //data


        //adapter
       LoadData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDilog();
            }


        });
        return view;

    }
    private void LoadData(){
        muon = new Phieu_Muon(getContext());
        list =muon.getAll();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        Phieu_Muon_Adapter adapter = new Phieu_Muon_Adapter(list , getContext());
        recyclerView.setAdapter(adapter);
    }
    private void showDilog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater =   getLayoutInflater();
        View view = inflater.inflate(R.layout.dilog_them_phieu_muon , null);
        Spinner spinnerThanhvien = view.findViewById(R.id.spnthanhvien);
        Spinner spinnersach = view.findViewById(R.id.sqnsach);

        getdatathanhvien(spinnerThanhvien);
        getdatasach(spinnersach);
        builder.setView(view);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
              //lấy mac thành viên
              HashMap<String , Object>  hsTV =( HashMap<String , Object> )spinnerThanhvien.getSelectedItem();
              int matv = (int) hsTV.get("MaTV");

              //lấy mac sách
              HashMap<String ,Object> hsS = ( HashMap<String ,Object> )spinnersach.getSelectedItem();
              int masach = (int) hsS.get("MaS");

              int tien = (int) hsS.get("giathue");

              themPhieumuon(matv  , masach , tien);
          }
      });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
});

        AlertDialog dialog  = builder.create();
        dialog.show();

    }

    private void getdatathanhvien(Spinner spinnerThanhVin){
        Thanh_Vien_DAO thanh_vien_dao = new Thanh_Vien_DAO(getContext());
        ArrayList<Thanh_Vien> list = thanh_vien_dao.getAllThanhVien();


        ArrayList<HashMap<String , Object>> listhashmap = new ArrayList<>();
        for(Thanh_Vien thanhVien :list){
            HashMap<String , Object> hs = new HashMap<>();
            hs.put("MaTV" , thanhVien.getMathanhvien());
            hs.put("hotenthanhvien" , thanhVien.getHotenthanhvien());

            listhashmap.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext() , listhashmap ,android.R.layout.simple_list_item_1 , new String[]{"hotenthanhvien"}, new int[]{android.R.id.text1});
        spinnerThanhVin.setAdapter(simpleAdapter);

    }
    private  void getdatasach(Spinner spinnerSach){
        Sach_Dao sach_dao = new Sach_Dao(getContext());
        ArrayList<Sach> list = sach_dao.getAllsach();


        ArrayList<HashMap<String , Object>> listhashmap = new ArrayList<>();
        for(Sach sach :list){
            HashMap<String , Object> hs = new HashMap<>();
            hs.put("MaS" , sach.getMasach());
            hs.put("tensach" , sach.getTensach());
            hs.put("giathue" , sach.getGiathue());
            listhashmap.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext() , listhashmap ,android.R.layout.simple_list_item_1 , new String[]{"tensach"}, new int[]{android.R.id.text1});
        spinnerSach.setAdapter(simpleAdapter);
    }
    private  void themPhieumuon(int matv, int masach , int tien){
        // lấy ma thut thư
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("loginPrefs" , Context.MODE_PRIVATE);
        String mathuthu = sharedPreferences.getString("MaTT" , "");

        //lấy ngày hiện tại
        Date curentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy" , Locale.getDefault());
        String ngay  = simpleDateFormat.format(curentTime);

        Phieu_Muon_MODEL  muonModel = new Phieu_Muon_MODEL(mathuthu ,matv , masach , ngay , 0 , tien );
        boolean kiemtra =muon.themphieumuon(muonModel);
        if(kiemtra){
            Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
            LoadData();
        }else{
            Toast.makeText(getContext(), "thất bại", Toast.LENGTH_SHORT).show();
        }

    }
}