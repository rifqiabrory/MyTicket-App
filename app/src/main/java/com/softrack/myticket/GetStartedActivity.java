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

public class GetStartedActivity extends AppCompatActivity {

    Button sign_in, btn_new_account;
    ImageView imageView2;
    TextView textView;
    Animation top_to_bottom, bottom_to_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        // load animation
        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);

        // load elements
        sign_in = findViewById(R.id.btn_sign_in);
        btn_new_account = findViewById(R.id.btn_new_account);
        imageView2 = findViewById(R.id.imageView2);
        textView = findViewById(R.id.textView);

        // run animation
        imageView2.setAnimation(top_to_bottom);
        textView.setAnimation(top_to_bottom);
        sign_in.setAnimation(bottom_to_top);
        btn_new_account.setAnimation(bottom_to_top);

        sign_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStartedActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btn_new_account.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStartedActivity.this, RegisterFirstActivity.class);
                startActivity(intent);
            }
        });
    }
}
