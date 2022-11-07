package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DAO.Thu_Thu;
import com.example.myapplication.Fragment.Fragment_Doanh_Thu;
import com.example.myapplication.Fragment.Fragment_QLLoaiSach;
import com.example.myapplication.Fragment.Fragment_QLPhieuMuon;
import com.example.myapplication.Fragment.Fragment_QLSach;
import com.example.myapplication.Fragment.Fragment_QLThanhVien;
import com.example.myapplication.Fragment.Fragment_top10;
import com.example.myapplication.Fragment.LietkeFragnment;
import com.example.myapplication.Fragment.Lietke_phieu_muonFragment;
import com.google.android.material.navigation.NavigationView;

public class Navigation_drawer_thu_thu extends AppCompatActivity {

    DrawerLayout drawerLayout ;
    Toolbar toolbar;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_thu_thu);
        drawerLayout = findViewById(R.id.navigation_drawer_2);
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        NavigationView view =findViewById(R.id.nav_view2);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle( this,drawerLayout  , toolbar , R.string.navgationdrawer_open , R.string.navgationdrawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Fragment_QLPhieuMuon qlPhieuMuon = new Fragment_QLPhieuMuon();
                        replacefm(qlPhieuMuon);
                        break;
                    case R.id.quanliloaisach:
                        Fragment_QLLoaiSach fragment_qlLoaiSach = new Fragment_QLLoaiSach();
                        replacefm(fragment_qlLoaiSach);
                        break;
                    case R.id.quanlisach:
                        Fragment_QLSach fragment_qlSach = new Fragment_QLSach();
                        replacefm(fragment_qlSach);
                        break;
                    case R.id.quanlithanhvien:
                        Fragment_QLThanhVien fragment_qlThanhVien = new Fragment_QLThanhVien();
                        replacefm(fragment_qlThanhVien);
                        break;
                    case R.id.top10:
                        Fragment_top10 fragment_top10 = new Fragment_top10();
                        replacefm(fragment_top10);
                        break;
                    case R.id.Doanhthu:
                        Fragment_Doanh_Thu fragment_doanh_thu = new Fragment_Doanh_Thu();
                        replacefm(fragment_doanh_thu);
                        break;
                    case R.id.LietKe:
                        LietkeFragnment lietkeFragnment = new LietkeFragnment();
                        replacefm(lietkeFragnment);
                        break;
                    case R.id.Lietkephieumuon:
                        Lietke_phieu_muonFragment lietke_phieu_muonFragment = new Lietke_phieu_muonFragment();
                        replacefm(lietke_phieu_muonFragment);
                        break;
                    case R.id.doimatkhau:
                        showdilogDoiMatKhau();
                        break;
                    case R.id.dangxuat:
                        intent = new Intent(Navigation_drawer_thu_thu.this , login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(Navigation_drawer_thu_thu.this, "đăng xuất thành công", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }
    public void replacefm(Fragment fragment){
        FragmentManager manager =getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.framer2 , fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }
    private void showdilogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setNegativeButton("Cập Nhập" , null)
                .setPositiveButton("Hủy" , null);
        LayoutInflater  inflater = getLayoutInflater();
        View view  = inflater.inflate(R.layout.dilog_doi_mat_khau , null);
        EditText old = view.findViewById(R.id.mkcu);
        EditText newp = view.findViewById(R.id.mkmoi);
        EditText newpre = view.findViewById(R.id.mkmoire);


        builder.setView(view);



        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String oldp = old.getText().toString();
                    String newpas = newp.getText().toString();
                    String repa = newpre.getText().toString();
                    if(newpas.equals(repa)){
                        SharedPreferences sharedPreferences  = getSharedPreferences("loginPrefs" , MODE_PRIVATE);
                        String matt  =sharedPreferences.getString("MaTT" , "");

                        //cập nhập
                        Thu_Thu thuThu = new Thu_Thu(Navigation_drawer_thu_thu.this);
                        boolean check =     thuThu.capnhapmatkhau(matt , oldp , newpas);
                        if(check){
                            Toast.makeText(Navigation_drawer_thu_thu.this, "cập nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Navigation_drawer_thu_thu.this ,login.class );

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        }else {
                            Toast.makeText(Navigation_drawer_thu_thu.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Navigation_drawer_thu_thu.this, "không trùng khướp", Toast.LENGTH_SHORT).show();
                    }

            }
        });

    }
}