package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.DAO.Phieu_Muon;
import com.example.myapplication.R;
import com.example.myapplication.model.Phieu_Muon_MODEL;

import java.util.ArrayList;

public class lietke_trongkhoang_phieumuon extends BaseAdapter {
    ArrayList<Phieu_Muon_MODEL> list = new ArrayList<>();
    Context context ;

    public lietke_trongkhoang_phieumuon(ArrayList<Phieu_Muon_MODEL> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
            return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_trong_khoang , parent , false);
        TextView view = convertView.findViewById(R.id.lietkemaPhieuMuontrongkhoang);
        TextView view2 = convertView.findViewById(R.id.lietkemasachtrongkhoang);
        TextView view3 = convertView.findViewById(R.id.lietkethanhvientrongkhoang);
        TextView view4 = convertView.findViewById(R.id.lietkengaymuontrongkhoang);
        Phieu_Muon_MODEL muonModel = list.get(position);
        view.setText(String.valueOf(muonModel.getMaphieumuon()));
        view2.setText(String.valueOf(muonModel.getMasach()));
        view3.setText(String.valueOf(muonModel.getMathanhvien()));
        view4.setText(muonModel.getNgay());

        return convertView;
    }
}
