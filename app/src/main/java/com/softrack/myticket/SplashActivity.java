package com.softrack.myticket;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    Animation splash_animation, bottom_to_top;
    ImageView app_logo;
    TextView desc_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // load animation
        splash_animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);

        // load elements
        app_logo = findViewById(R.id.app_logo);
        desc_logo = findViewById(R.id.desc_logo);

        // run animation
        app_logo.setAnimation(splash_animation);
        desc_logo.setAnimation(bottom_to_top);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, GetStartedActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
