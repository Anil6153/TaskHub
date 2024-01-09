package com.example.to_do;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.to_do.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000; // Splash duration in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Find the TextView and ImageView by their IDs
        TextView textTaskHub = findViewById(R.id.textTaskHub);
        ImageView imgV = findViewById(R.id.imgV);

        // Apply translation and alpha animations to the TextView
        textTaskHub.setTranslationX(-300f);
        textTaskHub.setAlpha(0f);

        // Apply translation and alpha animations to the ImageView
        imgV.setTranslationY(-300f);
        imgV.setAlpha(0f);

        // Example of combined translation and lpha animation for TextView
        textTaskHub.animate()
                .translationX(0f)  // Adjust this value for left-to-right translation
                .alpha(1f)
                .setDuration(1000)
                .setInterpolator(new AccelerateInterpolator())
                .start();

        // Example of combined translation and alpha animation for ImageView
        imgV.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(1000)
                .setInterpolator(new AccelerateInterpolator())
                .start();

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
