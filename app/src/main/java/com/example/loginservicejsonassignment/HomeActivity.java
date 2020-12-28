package com.example.loginservicejsonassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView tvShowName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvShowName = findViewById(R.id.tvSucUser);

        Bundle bundle=getIntent().getExtras();
        String username=bundle.getString("username");
        tvShowName.setText(("Hello "+username));
    }
}