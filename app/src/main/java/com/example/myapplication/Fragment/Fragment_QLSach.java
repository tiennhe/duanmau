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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.DAO.Loai_Sach_Dao;
import com.example.myapplication.DAO.Sach_Dao;
import com.example.myapplication.R;
import com.example.myapplication.adapter.SachAdapter;
import com.example.myapplication.model.Loai_Sach;
import com.example.myapplication.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;


public class Fragment_QLSach extends Fragment {
    RecyclerView recyclerView ;
    FloatingActionButton button ;
    ArrayList<Sach> list ;
    Sach_Dao dao;

    public Fragment_QLSach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__q_l_sach ,container , false);
        recyclerView = view.findViewById(R.id.recyclerview_sach);
        button = view.findViewById(R.id.float_sach);
        loaddata();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDilog();
            }
        });
        return view;
    }

    private void showDilog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dilog_them_sach , null);
        builder.setView(view);

        EditText edtten = view.findViewById(R.id.edttensach);
        EditText edttien = view.findViewById(R.id.edttien);
        Spinner spnloaisach = view.findViewById(R.id.spnerloaisach);
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),getdsLoaiSach(), android.R.layout.simple_list_item_1 , new String[]{"tenloai" } , new int[]{android.R.id.text1}
        );
        spnloaisach.setAdapter(simpleAdapter);
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tensach = edtten.getText().toString();
                int tien = Integer.parseInt(edttien.getText().toString());
                HashMap<String ,Object> hashMap =(HashMap<String, Object>) spnloaisach.getSelectedItem();
                int ma = (int) hashMap.get("MaLS");
           if(tensach.equalsIgnoreCase("n")){

           }

                boolean check = dao.themsach(tensach ,tien , ma);
                if(check){
                    Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                    loaddata();
                }else {
                    Toast.makeText(getContext(), "Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private ArrayList<HashMap<String , Object>> getdsLoaiSach(){
        Loai_Sach_Dao loai_sach_dao = new Loai_Sach_Dao(getContext());
        ArrayList<Loai_Sach> list = loai_sach_dao.getAllLoaiSach();
        ArrayList<HashMap<String , Object>> hashMapArrayList = new ArrayList<>();

        for(Loai_Sach sach :list){
            HashMap<String ,Object> hs = new HashMap<>();
            hs.put("MaLS" , sach.getMaloaisach());
            hs.put("tenloai" ,sach.getTenloaisach());
            hashMapArrayList.add(hs);
        }
        return hashMapArrayList;
    }

    private void loaddata() {
        dao = new Sach_Dao(getContext());
        list = dao.getAllsach();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        SachAdapter adapter = new SachAdapter(getContext() , list,getdsLoaiSach(),dao);
        recyclerView.setAdapter(adapter);
    }
}