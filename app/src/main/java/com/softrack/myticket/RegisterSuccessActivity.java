package com.softrack.myticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterSuccessActivity extends AppCompatActivity {

    Animation splash_animation, bottom_to_top,top_to_bottom;
    Button btn_explore;
    ImageView imageView4;
    TextView textView2, textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);

        // load animation
        splash_animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);

        // load elements
        btn_explore = findViewById(R.id.btn_explore);
        imageView4 = findViewById(R.id.imageView4);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        // run animation
        imageView4.setAnimation(splash_animation);
        textView2.setAnimation(top_to_bottom);
        textView3.setAnimation(top_to_bottom);
        btn_explore.setAnimation(bottom_to_top);

        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterSuccessActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
