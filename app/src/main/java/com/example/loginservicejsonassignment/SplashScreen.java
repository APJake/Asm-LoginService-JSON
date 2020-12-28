package com.example.loginservicejsonassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static final int FLASH_TIME = 2000;

    ImageView imgLogo;
    TextView tvTitle;
    Animation anim_top, anim_bot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imgLogo=findViewById(R.id.imgLogo);
        tvTitle=findViewById(R.id.tvLogoTitle);

        anim_top = AnimationUtils.loadAnimation(this, R.anim.anim_from_top);
        anim_bot = AnimationUtils.loadAnimation(this, R.anim.anim_from_bot);

        imgLogo.setAnimation(anim_top);
        tvTitle.setAnimation(anim_bot);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },FLASH_TIME);
    }
}