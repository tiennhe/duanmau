package com.example.myapplication.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.DAO.Thong_Ke_Dao;
import com.example.myapplication.R;

import java.util.Calendar;

public class Fragment_Doanh_Thu extends Fragment {


    public Fragment_Doanh_Thu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__doanh__thu, container, false);
        EditText edtstart = view.findViewById(R.id.edtStart);
        EditText edtstop = view.findViewById(R.id.edtStop);
        Button button = view.findViewById(R.id.thongke);
        TextView tvketqua = view.findViewById(R.id.tvketquathongke);
        Calendar calendar = Calendar.getInstance();

        edtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =     new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String ngay= "";
                        String thang = "";
                        if(dayOfMonth<10){
                            ngay="0"+dayOfMonth;
                        }else {
                            ngay = String.valueOf(dayOfMonth);
                        }
                        if(month+1<10){
                            thang="0"+(month+1);
                        }else{
                            thang = String.valueOf(month+1);
                        }
                        edtstart.setText(year+"/"+(thang)+"/"+ngay);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

            datePickerDialog.show();
            }
        });
        edtstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    DatePickerDialog datePickerDialog =     new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String ngay= "";
                            String thang = "";
                            if(dayOfMonth<10){
                                ngay="0"+dayOfMonth;
                            }else {
                                ngay = String.valueOf(dayOfMonth);
                            }
                            if(month+1<10){
                                thang="0"+(month+1);
                            }else{
                                thang = String.valueOf(month+1);
                            }
                            edtstop.setText(year+"/"+(thang)+"/"+ngay);
                        }
                    },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );

                    datePickerDialog.show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thong_Ke_Dao thong_ke_dao = new Thong_Ke_Dao(getContext());
                String ngaybatdau = edtstart.getText().toString();
                String ngayketthu = edtstop.getText().toString();
                int doanhthu = thong_ke_dao.getdoanhthu(ngaybatdau , ngayketthu);
                tvketqua.setText("Doanh thu là:"+doanhthu+"VND");
            }
        });
        return view;
    }
}