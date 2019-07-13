package com.softrack.myticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessBuyTicketActivity extends AppCompatActivity {

    Button btn_dashboard, btn_view_ticket;
    TextView textView2, textView3;
    ImageView imageView4;
    Animation splash_animation, top_to_bottom, bottom_to_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_buy_ticket);

        // load animation
        splash_animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);

        // load elements
        btn_dashboard = findViewById(R.id.btn_dashboard);
        btn_view_ticket = findViewById(R.id.btn_view_ticket);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        imageView4 = findViewById(R.id.imageView4);

        // run animation
        imageView4.setAnimation(splash_animation);
        textView2.setAnimation(top_to_bottom);
        textView3.setAnimation(top_to_bottom);
        btn_dashboard.setAnimation(bottom_to_top);
        btn_view_ticket.setAnimation(bottom_to_top);



    }
}
