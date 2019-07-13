package com.softrack.myticket;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckoutTicketActivity extends AppCompatActivity {

    Button btn_pay, btn_minus, btn_plus;
    TextView text_order, balance, total;
    ImageView not_enought;
    Integer default_order_value = 1;
    Integer dummyBalance = 200, defaultTotal = 0, dummyPrice = 75;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_ticket);

        // load elements
        btn_pay = findViewById(R.id.btn_pay);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        text_order = findViewById(R.id.text_order);
        balance = findViewById(R.id.balance);
        total = findViewById(R.id.total);
        not_enought = findViewById(R.id.not_enought);

        // default
        text_order.setText(default_order_value.toString());
        balance.setText("$ "+dummyBalance+"");
        defaultTotal = dummyPrice * default_order_value;
        total.setText("$ "+defaultTotal+"");

        // init
        btn_minus.animate().alpha(0).setDuration(300).start();
        btn_minus.setEnabled(false);
        not_enought.setVisibility(View.GONE);

        btn_plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                default_order_value+=1;
                text_order.setText(default_order_value.toString());

                if (default_order_value > 1) {
                    btn_minus.animate().alpha(1).setDuration(300).start();
                    btn_minus.setEnabled(true);
                }

                defaultTotal = dummyPrice * default_order_value;
                total.setText("$ "+defaultTotal+"");

                if (defaultTotal > dummyBalance) {
                    btn_pay.animate().translationY(250).alpha(0).setDuration(350).start();
                    btn_pay.setEnabled(false);
                    balance.setTextColor(Color.parseColor("#D1206B"));
                    not_enought.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                default_order_value-=1;
                text_order.setText(default_order_value.toString());

                if (default_order_value < 2) {
                    btn_minus.animate().alpha(0).setDuration(300).start();
                    btn_minus.setEnabled(false);
                }

                defaultTotal = dummyPrice * default_order_value;
                total.setText("$ "+defaultTotal+"");

                if (default_order_value < dummyBalance) {
                    btn_pay.animate().translationY(0).alpha(1).setDuration(350).start();
                    btn_pay.setEnabled(true);
                    balance.setTextColor(Color.parseColor("#203DD1"));
                    not_enought.setVisibility(View.GONE);
                }
            }
        });

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutTicketActivity.this, SuccessBuyTicketActivity.class);
                startActivity(intent);
            }
        });
    }
}
