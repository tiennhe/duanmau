package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.DAO.Loai_Sach_Dao;
import com.example.myapplication.DAO.Sach_Dao;
import com.example.myapplication.R;
import com.example.myapplication.adapter.LietkeAdapter;
import com.example.myapplication.adapter.Loai_Sach_Adapter;
import com.example.myapplication.adapter.SachAdapter;
import com.example.myapplication.model.Loai_Sach;
import com.example.myapplication.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LietkeFragnment extends Fragment {

    Sach_Dao dao ;
    RecyclerView recyclerView ;

    ArrayList<Sach> list ;

    public LietkeFragnment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lietke_fragnment, container, false);
        recyclerView = view.findViewById(R.id.rcllietke);
        loaddata();
        return view;

    }
    public void loaddata(){
        dao = new Sach_Dao(getContext());
        list = dao.lietke();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        LietkeAdapter adapter = new LietkeAdapter(getContext() , list);
        recyclerView.setAdapter(adapter);
    }
}