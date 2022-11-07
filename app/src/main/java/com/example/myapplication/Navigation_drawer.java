package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.Fragment.Fragment_QLPhieuMuon;
import com.google.android.material.navigation.NavigationView;

public class Navigation_drawer extends AppCompatActivity {
    DrawerLayout drawerLayout ;
    Toolbar toolbar;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        drawerLayout = findViewById(R.id.navigation_drawer);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView view =findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle( this,drawerLayout  , toolbar , R.string.navgationdrawer_open , R.string.navgationdrawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        replacefm(new Fragment_QLPhieuMuon());
                        break;
                    case R.id.quanliloaisach:

                        break;
                    case R.id.quanlisach:

                        break;
                    case R.id.quanlithanhvien:

                        break;
                    case R.id.top10:

                        break;
                    case R.id.Doanhthu:

                        break;
                    case R.id.doimatkhau:

                        break;
                    case R.id.dangxuat:
                        intent = new Intent(Navigation_drawer.this , login.class);
                        startActivity(intent);
                        Toast.makeText(Navigation_drawer.this, "đăng xuất thành công", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }
    public void replacefm(Fragment fragment){
        FragmentManager manager =getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.framer , fragment).commit();
    }
}