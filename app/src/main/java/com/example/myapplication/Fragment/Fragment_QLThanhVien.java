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
import com.example.myapplication.DAO.Thanh_Vien_DAO;
import com.example.myapplication.R;
import com.example.myapplication.adapter.Loai_Sach_Adapter;
import com.example.myapplication.adapter.ThanhVienAdapter;
import com.example.myapplication.model.Thanh_Vien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Fragment_QLThanhVien extends Fragment {
    RecyclerView recyclerView ;
    FloatingActionButton button ;
    ArrayList<Thanh_Vien> list  ;
    Thanh_Vien_DAO thanh_vien_dao ;
    public Fragment_QLThanhVien() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__q_l_thanh_vien, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_thanhvien);
        button = view.findViewById(R.id.float_thanh_vien);
        loadData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendilog();
            }
        });
        return view;
    }

    private void opendilog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dilog_them_thanh_vien , null);
        builder.setView(view);
        EditText edthoten = view.findViewById(R.id.edthotenthanhvien);
        EditText edtnamsinh = view.findViewById(R.id.edtnamsinh);
        builder.setNegativeButton("thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edthoten.getText().toString();
                String namsinh =edtnamsinh.getText().toString();
                boolean check = thanh_vien_dao.themthanhvien(hoten , namsinh);
                if(check){
                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(), "Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
       AlertDialog dialog = builder.create();
       dialog.show();


    }

    private void loadData() {
        thanh_vien_dao = new Thanh_Vien_DAO(getContext());
        list = thanh_vien_dao.getAllThanhVien();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext() , list , thanh_vien_dao);
        recyclerView.setAdapter(adapter);
    }
}