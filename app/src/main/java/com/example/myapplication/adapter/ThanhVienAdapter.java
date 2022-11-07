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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Thanh_Vien_DAO;
import com.example.myapplication.R;
import com.example.myapplication.model.Thanh_Vien;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.Viewholder>{
    private Context context;
    private ArrayList<Thanh_Vien> list;
    private Thanh_Vien_DAO thanh_vien_dao;

    public ThanhVienAdapter(Context context, ArrayList<Thanh_Vien> list,Thanh_Vien_DAO thanh_vien_dao) {
        this.context = context;
        this.list = list;
        this.thanh_vien_dao = thanh_vien_dao;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcl_thanh_vien , parent , false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txtma.setText("Ma Thanh Vien:" +String.valueOf(list.get(position).getMathanhvien()));
        holder.txttenthanhvien.setText("Họ Ten Thanh Vien:"+(list.get(position).getHotenthanhvien()));
        holder.txtnamsinh.setText("Năm sinh:"+list.get(position).getNamsinh());
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDilog(list.get(holder.getAdapterPosition()));
            }
        });
        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = thanh_vien_dao.xoathongtin(list.get(holder.getAdapterPosition()).getMathanhvien());
                if(check==1){
                    Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    loatData();
                }
                else if(check == 0){
                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                }
                else if(check==-1){
                    Toast.makeText(context, "Tồn tại phiếu mượn k được xóa", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView txtma , txttenthanhvien , txtnamsinh;
        ImageView img1 , img2;
        public Viewholder(@NonNull View itemView) {

            super(itemView);
            txtma = itemView.findViewById(R.id.txtmathanhvien);
            txttenthanhvien = itemView.findViewById(R.id.txthoten);
            txtnamsinh = itemView.findViewById(R.id.txtnamsinh);
            img1 = itemView.findViewById(R.id.imgsuathanhvien);
            img2 = itemView.findViewById(R.id.imgxoathanhvien);

        }
    }
    private void showDilog(Thanh_Vien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dilog_sua_thanh_vien , null);
        builder.setView(view);

        TextView ma = view.findViewById(R.id.txtmathnahviensua);
        EditText edtten = view.findViewById(R.id.suahoten);
        EditText edtnamsinh = view.findViewById(R.id.suanamsinh);

        ma.setText("Mã Thành Viên:"+thanhVien.getMathanhvien());
        edtten.setText(thanhVien.getHotenthanhvien());
        edtnamsinh.setText(thanhVien.getNamsinh());
        builder.setNegativeButton("Cập nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edtten.getText().toString();
                String namsinh = edtnamsinh.getText().toString();
                int id = thanhVien.getMathanhvien();


                boolean check = thanh_vien_dao.capnhap(id , hoten , namsinh);
                if(check){
                    Toast.makeText(context, "cập nhaapj thành coong", Toast.LENGTH_SHORT).show();
                    loatData();
                }else {
                    Toast.makeText(context, "không thành công", Toast.LENGTH_SHORT).show();
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
    private void loatData(){
        list.clear();
        list = thanh_vien_dao.getAllThanhVien();
        notifyDataSetChanged();
    }
}
