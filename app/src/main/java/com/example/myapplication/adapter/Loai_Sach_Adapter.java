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
import com.example.myapplication.model.Loai_Sach;

import java.util.ArrayList;

public class Loai_Sach_Adapter extends RecyclerView.Adapter<Loai_Sach_Adapter.viewHolder>{
   private Context context ;
   private ArrayList<Loai_Sach> list ;

    public Loai_Sach_Adapter(Context context, ArrayList<Loai_Sach> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_loai_sach , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtxmaloai.setText("Mã Sách:"+String.valueOf(list.get(position).getMaloaisach()));
        holder.txttenlaoi.setText("Tên Loại:" +list.get(position).getTenloaisach());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder {
        TextView txtxmaloai , txttenlaoi;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtxmaloai = itemView.findViewById(R.id.txtmaloaisachitem);
            txttenlaoi = itemView.findViewById(R.id.txttenloaisachitem);

        }
    }
}
