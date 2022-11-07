package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Phieu_Muon_MODEL;

import java.util.ArrayList;

public class LietKe_PhieuMuon_Adapter extends RecyclerView.Adapter<LietKe_PhieuMuon_Adapter.viewHolder>{
    private Context context;
    private ArrayList<Phieu_Muon_MODEL> list;

    public LietKe_PhieuMuon_Adapter(Context context, ArrayList<Phieu_Muon_MODEL> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_phieu_muon , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txt1.setText("Ma phieu muon:"+list.get(position).getMaphieumuon());

        holder.txt3.setText("Ma thanh vien:"+list.get(position).getMathanhvien());
        holder.txt2.setText("Ma  sach:"+list.get(position).getMasach());
        holder.txt4.setText("Ngay muon:"+list.get(position).getNgay());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt1 , txt2 , txt3 , txt4;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.lietkemaPhieuMuon);
            txt2 = itemView.findViewById(R.id.lietkemasach);
            txt3 = itemView.findViewById(R.id.lietkethanhvien);
            txt4 = itemView.findViewById(R.id.lietkengaymuon);
        }
    }
}
