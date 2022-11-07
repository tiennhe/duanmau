package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.DAO.Phieu_Muon;
import com.example.myapplication.DAO.Thong_Ke_Dao;
import com.example.myapplication.R;
import com.example.myapplication.adapter.LietKe_PhieuMuon_Adapter;
import com.example.myapplication.adapter.Top10_adapter;
import com.example.myapplication.model.Phieu_Muon_MODEL;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;


public class Lietke_phieu_muonFragment extends Fragment {

    RecyclerView recyclerView ;
    public Lietke_phieu_muonFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lietke_phieu_muon, container, false);
        recyclerView = view.findViewById(R.id.list_phieu_muon);


        Phieu_Muon dao = new Phieu_Muon(getContext());
        ArrayList<Phieu_Muon_MODEL> list = dao.getlietke();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        LietKe_PhieuMuon_Adapter adapter = new LietKe_PhieuMuon_Adapter(getContext() , list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}