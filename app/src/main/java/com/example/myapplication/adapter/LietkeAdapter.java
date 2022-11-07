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
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class LietkeAdapter extends RecyclerView.Adapter<LietkeAdapter.viewHolder>{
    private Context context ;
    private ArrayList<Sach> list ;

    public LietkeAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View  view = inflater.inflate(R.layout.item_liet_ke , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txt1.setText("Mã Sách:" +String.valueOf(list.get(position).getMasach()));
        holder.txt2.setText("Tên Sách:" +list.get(position).getTensach());
        holder.txt3.setText("Gía Thuê:" +list.get(position).getGiathue());
        holder.txt4.setText("Mã Loại Sách:" +list.get(position).getMaloai());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt1 , txt2 , txt3 , txt4;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.lietkeMaSach);
            txt2 = itemView.findViewById(R.id.lietkeTenSach);
            txt3 = itemView.findViewById(R.id.lietkeGiaThue);
            txt4 = itemView.findViewById(R.id.lietkemaloai);
        }
    }
}
