package com.example.loginproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edtUser = findViewById(R.id.edtUsername);
        EditText edtPw = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = edtUser.getText().toString();
                String pw = edtPw.getText().toString();
                if(usr.equals("")){
                    Log.e("MainActivity", "Please enter Username");
                }else if(pw.equals("")){
                    Log.e("MainActivity", "Please enter Password");
                }else{
                    Log.d("MainActivity", "Username : " + usr);
//                    try {
//                        run();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
                }
                Intent intent = new Intent(MainActivity.this, DataList.class);
                intent.putExtra("UsrName", usr);
                startActivity(intent);

            }
        });
    }
}