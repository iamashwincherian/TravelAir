package com.example.travelair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    private static int FLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
/*
                if(SaveSharedPreference.getFirstLaunch(SplashActivity.this).toString() == "true")
                {
                    Toast.makeText(SplashActivity.this, SaveSharedPreference.getFirstLaunch(SplashActivity.this), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), login.class);
                    startActivity(i);
                    finish();
                }
*/
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }
        },FLASH_TIME_OUT);
    }
}
