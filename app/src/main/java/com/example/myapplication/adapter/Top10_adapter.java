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

public class Top10_adapter extends RecyclerView.Adapter<Top10_adapter.viewHolder>{
    private Context context ;
    private ArrayList<Sach> list;

    public Top10_adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View view = inflater.inflate(R.layout.item_top10 , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtmasach.setText("mã sách:" +String.valueOf(list.get(position).getMasach()));
        holder.txttensach.setText("tên sách:"+list.get(position).getTensach());
        holder.txtsoluongsach.setText("số lượng mượn:"+String.valueOf(list.get(position).getSoluongmuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtmasach , txttensach , txtsoluongsach;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtmasach = itemView.findViewById(R.id.txtmasach_top10);
            txttensach = itemView.findViewById(R.id.txttensach_top10);
            txtsoluongsach = itemView.findViewById(R.id.txtsoluonmuon_top10);
        }
    }


}
