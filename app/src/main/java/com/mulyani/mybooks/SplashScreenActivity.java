package com.mulyani.mybooks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.mulyani.mybooks.View.Authentication.AuthenticationActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();

        FirebaseApp.initializeApp(this);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), AuthenticationActivity.class));
            finish();
        }, 3000L);
    }

    private void initView() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView textView = findViewById(R.id.textView);
    }
}
