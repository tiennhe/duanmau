package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.DAO.Phieu_Muon;
import com.example.myapplication.adapter.Phieu_Muon_Adapter;
import com.example.myapplication.adapter.lietke_trongkhoang_phieumuon;
import com.example.myapplication.model.Phieu_Muon_MODEL;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    ListView view;
    Phieu_Muon muon ;
    ArrayList<Phieu_Muon_MODEL> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        view = findViewById(R.id.lisst_phieumuon);
        loaddata();
//            ArrayList<Phieu_Muon_MODEL> list = muon.getAll();
//            lietke_trongkhoang_phieumuon phieumuon =new lietke_trongkhoang_phieumuon(list , this);
//            view.setAdapter(phieumuon);
    }
    public void loaddata(){
       muon = new Phieu_Muon(this);
        list =muon.getAll();


        lietke_trongkhoang_phieumuon adapter = new lietke_trongkhoang_phieumuon(list , this);
        view.setAdapter(adapter);
    }
}