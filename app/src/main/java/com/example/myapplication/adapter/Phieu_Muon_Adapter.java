package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Phieu_Muon;
import com.example.myapplication.R;
import com.example.myapplication.model.Phieu_Muon_MODEL;

import java.util.ArrayList;

public class Phieu_Muon_Adapter extends RecyclerView.Adapter<Phieu_Muon_Adapter.viewholder> {
private ArrayList<Phieu_Muon_MODEL> list ;
private Context context;

    public Phieu_Muon_Adapter(ArrayList<Phieu_Muon_MODEL> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View view = inflater.inflate(R.layout.item_recycle_phieu_muon , parent , false);
        return  new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.txtmapm.setText("Ma phieu muon:"+list.get(position).getMaphieumuon());
        holder.txtmatt.setText("Ma thu thu:"+list.get(position).getMathuthu());
        holder.txtmatv.setText("Ma thanh vien:"+list.get(position).getMathanhvien());
        holder.txtmas.setText("Ma  sach:"+list.get(position).getMasach());
        holder.txtngay.setText("Ngay muon:"+list.get(position).getNgay());
        String trangthai = "";
       if(list.get(position).getTrasach()==1){
            trangthai = "Da tra sach";
            holder.btntrasach.setVisibility(View.GONE);
       }
       else{
           trangthai = "chua tra sach";
           holder.btntrasach.setVisibility(View.VISIBLE);
       }
        holder.txttrangthai.setText("Trang thai:"+trangthai);
        holder.txttien.setText("Tien muon:"+list.get(position).getTienthue());
        holder.txttentv.setText("Ten thanh vien:"+list.get(position).getTentv());
        holder.txttentt.setText("Ten thu thu:"+list.get(position).getTentt());
        holder.txttens.setText("Ten sach:"+list.get(position).getTensach());
        holder.btntrasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phieu_Muon muon = new Phieu_Muon(context);
                boolean kiemtra = muon.thanhDoitrangthai(list.get(holder.getAdapterPosition()).getMaphieumuon());
                if(kiemtra==true){
                    list.clear();
                    list  = muon.getAll();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "thay doi that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder {
        TextView txtmapm , txtmatt , txtmatv , txtmas , txtngay , txttrangthai , txttien , txttentv , txttentt , txttens;
        Button btntrasach;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtmapm = itemView.findViewById(R.id.txtmaphieumuon);
            txtmas = itemView.findViewById(R.id.txtmasach);
            txtmatt = itemView.findViewById(R.id.txtmathuthu);
            txtmatv = itemView.findViewById(R.id.txtmathanhvien);
            txtngay = itemView.findViewById(R.id.txtngay);
            txttrangthai = itemView.findViewById(R.id.txttrasach);
            txttien = itemView.findViewById(R.id.txttienthue);
            txttentv = itemView.findViewById(R.id.txttenthanhvien);
            txttentt = itemView.findViewById(R.id.txttenthuthu);
            txttens = itemView.findViewById(R.id.txttensach);

            btntrasach = itemView.findViewById(R.id.btntra);
        }
    }
}
