package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.DAO.Thu_Thu;

public class login extends AppCompatActivity {
    CheckBox checkBox ;
    ImageView imgshowpass;
    EditText passwwold , username;
    Button btndangnhap , btndangki;
    Intent  intent ;
    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor1 ,editor2 ;
    String USERNAME_KEY = "user";
    String PASSWORLD_KEY = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Thu_Thu thuThu = new Thu_Thu(this);
        username = findViewById(R.id.taikhoan);
         passwwold = findViewById(R.id.matkhau);
         imgshowpass = findViewById(R.id.show_height_pass);
         checkBox = findViewById(R.id.checkbox);
         btndangnhap = findViewById(R.id.btndangnhap);
         btndangki = findViewById(R.id.btndangki);
         sharedPreferences1 = getSharedPreferences("loginPrefs" , MODE_PRIVATE);
         editor1 =sharedPreferences1.edit();
         username.setText(sharedPreferences1.getString(USERNAME_KEY , ""));
         passwwold.setText((sharedPreferences1.getString(PASSWORLD_KEY , "")));
         btndangnhap.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                String user = username.getText().toString();
                String pass = passwwold.getText().toString();
                if(thuThu.checkdangnhap(user  , pass)){

                    editor1.putString("MaTT" , user);
                    //lưu dữ liệu
                    editor1.commit();
                    intent = new Intent(login.this , Navigation_drawer_thu_thu.class);
                    startActivity(intent);
                    remember();

                }else {
                    Toast.makeText(login.this, "user và pass không đúng", Toast.LENGTH_SHORT).show();
                }

             }
         });
           imgshowpass.setImageResource(R.drawable.baseline_visibility_black_24dp);
           imgshowpass.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(passwwold.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                       passwwold.setTransformationMethod(PasswordTransformationMethod.getInstance());
                       imgshowpass.setImageResource(R.drawable.baseline_visibility_black_24dp);
                   }else{
                       passwwold.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                       imgshowpass.setImageResource(R.drawable.baseline_visibility_off_black_24dp);
                   }
               }
           });
    }
    public  void remember(){
        if(checkBox.isChecked()){
            editor1 = sharedPreferences1.edit();
            editor1.putString(USERNAME_KEY , username.getText().toString());
            editor1.putString(PASSWORLD_KEY , passwwold.getText().toString());
            editor1.commit();
        }
    }
}