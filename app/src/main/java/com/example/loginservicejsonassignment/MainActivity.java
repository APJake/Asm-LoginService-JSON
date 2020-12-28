package com.example.loginservicejsonassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginservicejsonassignment.mine.LoginService;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        edtUsername=findViewById(R.id.edt_username);
        edtPassword=findViewById(R.id.edt_password);
        btnLogin=findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LoginService.class);
                intent.putExtra("username",edtUsername.getText().toString());
                intent.putExtra("password",edtPassword.getText().toString());
                startService(intent);
            }
        });
    }
}