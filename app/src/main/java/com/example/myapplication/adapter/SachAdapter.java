package com.example.myapplication.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Sach_Dao;
import com.example.myapplication.R;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;
import java.util.HashMap;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.viewHolder>{
    private Context context ;
    private ArrayList<Sach> list ;
    private  ArrayList<HashMap<String , Object>> hashMapArrayList;
    private Sach_Dao dao;

    public SachAdapter(Context context, ArrayList<Sach> list , ArrayList<HashMap<String , Object>>hashMapArrayList , Sach_Dao dao) {
        this.context = context;
        this.list = list;
        this.hashMapArrayList = hashMapArrayList;
        this.dao = dao;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view  =inflater.inflate(R.layout.item_sach,parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtmas.setText("Mã Sách:" +list.get(position).getMasach());
        holder.txttensach.setText("Tên Sách:" +list.get(position).getTensach());
        holder.txtgiathue.setText("Gía Thuê:" +list.get(position).getGiathue());
        holder.txtmaloai.setText("Mã Loại Sách:" +list.get(position).getMaloai());
        holder.txttenloai.setText("Tên Loại Sách:" +list.get(position).getTenloai());
        holder.im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDilog(list.get(holder.getAdapterPosition()));
            }
        });
        holder.im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long check = dao.xoasach(list.get(holder.getAdapterPosition()).getMasach());
                if(check==1){
                    Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                    loaddata();
                }else if(check==0){
                    Toast.makeText(context, "không thành công", Toast.LENGTH_SHORT).show();
                }else if(check ==-1){
                    Toast.makeText(context, "không được xóa vì có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void showDilog(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dilog_sua_sach , null);
        builder.setView(view);


        EditText edttensach = view.findViewById(R.id.edttensachedit);
        EditText edtTien = view.findViewById(R.id.edttienedit);
        TextView txtMa = view.findViewById(R.id.txtmasachedit);
        Spinner spinnerloaisach =view.findViewById(R.id.spnerloaisachedit);
        txtMa.setText("Mã Sach:"+sach.getMasach());
        edttensach.setText( sach.getTensach());
        edtTien.setText(String.valueOf(sach.getGiathue()));

        SimpleAdapter simpleAdapter = new SimpleAdapter(context , hashMapArrayList , android.R.layout.simple_list_item_1 , new String[]{"tenloai"} , new int[]{android.R.id.text1});
        spinnerloaisach.setAdapter(simpleAdapter);
        
        int index = 0;
        int position = -1;
        for(HashMap<String , Object> item :hashMapArrayList){
            if((int)item.get("MaLS")==sach.getMaloai()){
                position = index;
            }
            index++;
        }
        spinnerloaisach.setSelection(position);
        builder.setNegativeButton("Cập nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                {
                    String tensach = edttensach.getText().toString();
                    int tien = Integer.parseInt(edtTien.getText().toString());
                    HashMap<String ,Object> hashMap =(HashMap<String, Object>) spinnerloaisach.getSelectedItem();
                    int ma = (int) hashMap.get("MaLS");

                    boolean check = dao.capnhapsach( sach.getMasach(), tensach ,tien , ma);
                    if(check){
                        Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                        loaddata();
                    }else {
                        Toast.makeText(context, "Thất Bại", Toast.LENGTH_SHORT).show();
                    }
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

    private void loaddata() {
        list.clear();
        list = dao.getAllsach();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView txtmas ,txttensach , txtgiathue , txtmaloai , txttenloai;
        ImageView im1 ,im2;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtmas = itemView.findViewById(R.id.txtMaSach);
            txttensach = itemView.findViewById(R.id.txtTenSach);
            txtgiathue = itemView.findViewById(R.id.txtGiaThue);
            txtmaloai = itemView.findViewById(R.id.txtMaLoaiSach);
            txttenloai = itemView.findViewById(R.id.txtTenLoaiSach);
            im1 = itemView.findViewById(R.id.imgsuasach);
            im2 = itemView.findViewById(R.id.imgxoasach);

        }
    }
}
