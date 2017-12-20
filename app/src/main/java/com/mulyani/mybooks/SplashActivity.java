package com.mulyani.mybooks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mulyani.mybooks.View.Autentikasi.AutentikasiActivity;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progresBar;
    private TextView tvSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), AutentikasiActivity.class));
                finish(); /*finish biar splash screen tidak berulang*/
            }
        }, 3000L); //3000L = durasi 3 detik
    }

    private void initView() {
        progresBar = findViewById(R.id.progresBar);
        tvSplash = findViewById(R.id.tvSplash);
    }
}
