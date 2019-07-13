package com.softrack.myticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TicketDetailActivity extends AppCompatActivity {

    Button btn_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        btn_buy = findViewById(R.id.btn_buy);

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicketDetailActivity.this, CheckoutTicketActivity.class);
                startActivity(intent);
            }
        });
    }
}
