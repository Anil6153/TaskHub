package com.example.to_do;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000; // Splash duration in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Find the TextView by its ID
        TextView textTaskHub = findViewById(R.id.textTaskHub);

        // Example of translation animation
        textTaskHub.setTranslationX(-200f);
        textTaskHub.animate().translationX(0f).setDuration(1000).setInterpolator(new AccelerateInterpolator()).start();

        // Example of alpha animation
        textTaskHub.setAlpha(0f);
        textTaskHub.animate().alpha(1f).setDuration(1000).setInterpolator(new AccelerateInterpolator()).start();

        // Delayed handler to transition to MainActivity after SPLASH_DURATION
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                // Finish SplashActivity to prevent going back to it
                finish();
            }
        }, SPLASH_DURATION);
    }
}
