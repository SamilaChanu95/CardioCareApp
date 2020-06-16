package com.example.cardiocareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(7000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent menuIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(menuIntent);
                }
            }
        };

        thread.start();
    }

    /*
        @Override
        protected void onPause() {
            super.onPause();
            finish();
        }
    */
}
