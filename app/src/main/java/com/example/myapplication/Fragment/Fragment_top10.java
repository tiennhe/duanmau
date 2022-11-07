package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.DAO.Thong_Ke_Dao;
import com.example.myapplication.R;
import com.example.myapplication.adapter.Top10_adapter;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;


public class Fragment_top10 extends Fragment {

        RecyclerView recyclerView ;

    public Fragment_top10() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top10, container, false);
        recyclerView = view.findViewById(R.id.rcytop10);

        Thong_Ke_Dao thong_ke_dao = new Thong_Ke_Dao(getContext());
        ArrayList<Sach> list = thong_ke_dao.gettop10();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        Top10_adapter adapter = new Top10_adapter(getContext() , list);
        recyclerView.setAdapter(adapter);

        return view;
    }
}