package com.example.myapplication.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DAO.Loai_Sach_Dao;
import com.example.myapplication.R;
import com.example.myapplication.adapter.Loai_Sach_Adapter;
import com.example.myapplication.model.Loai_Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Fragment_QLLoaiSach extends Fragment {
    Loai_Sach_Dao dao ;
    RecyclerView recyclerView ;
    FloatingActionButton button ;
    ArrayList<Loai_Sach> list ;

    public Fragment_QLLoaiSach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment__q_l_loai_sach, container, false);
        recyclerView = view.findViewById(R.id.rcv_loai_sach);
        button = view.findViewById(R.id.float_them_loai_sach);
        loadData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDilog();
            }
        });
        return view;
    }

    private void openDilog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View  view = inflater.inflate(R.layout.dilog_them_loai_sach , null);
        EditText edtthemloaisach = view.findViewById(R.id.edttthemloaisach);

        builder.setView(view);
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenloai = edtthemloaisach.getText().toString();
                if(dao.themLoaiSach(tenloai)){
                    loadData();
                    Toast.makeText(getContext(), "thêm thành công", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog  = builder.create();
        dialog.show();
    }

    private void loadData() {
        dao = new Loai_Sach_Dao(getContext());
        list = dao.getAllLoaiSach();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        Loai_Sach_Adapter adapter = new Loai_Sach_Adapter(getContext() , list);
        recyclerView.setAdapter(adapter);
    }
}