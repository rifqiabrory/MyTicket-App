package com.softrack.myticket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class SignInActivity extends AppCompatActivity {

    TextView new_account;
    EditText username, password;
    Button btn_signIn;
    DatabaseReference reference;

    //user key
    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        new_account = findViewById(R.id.new_account);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_signIn = findViewById(R.id.btn_signIn);

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = username.getText().toString();
                final String pwd = password.getText().toString();
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(user);

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            final String pass = dataSnapshot.child("password").getValue().toString();
                            if (pwd.equals(pass)) {
                                // save username or user access to local phone storage(ex. localStorage)
                                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(username_key,username.getText().toString());
                                editor.apply();

                                Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Password is not correct.", Toast.LENGTH_SHORT);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Username doesn't exist.", Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, RegisterFirstActivity.class);
                startActivity(intent);
            }
        });
    }
}
