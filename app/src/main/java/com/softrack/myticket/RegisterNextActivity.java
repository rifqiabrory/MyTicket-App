package com.softrack.myticket;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class RegisterNextActivity extends AppCompatActivity {

    LinearLayout btn_back;
    Button upload, btn_create;
    ImageView photo_register;
    EditText full_name, hobby;
    Uri photo_location;
    Integer photo_max = 1;

    // Firebase
    DatabaseReference reference;
    StorageReference storageReference;

    // define user key
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_next);
        getUsername();

        btn_back = findViewById(R.id.btn_back);
        upload = findViewById(R.id.upload);
        btn_create = findViewById(R.id.btn_create);
        photo_register = findViewById(R.id.photo_register);
        full_name = findViewById(R.id.full_name);
        hobby = findViewById(R.id.hobby);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterNextActivity.this, RegisterFirstActivity.class);
                startActivity(intent);
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change state
                btn_create.setEnabled(false);
                btn_create.setText("Processing...");

                reference = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(username_key_new);
                storageReference = FirebaseStorage.getInstance().getReference().child("Photos").child(username_key_new);

                if(photo_location != null){
                    StorageReference sR = storageReference.child(System.currentTimeMillis() + "." + getFileExtention(photo_location));

                    sR.putFile(photo_location).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String uri_photo = taskSnapshot.getUploadSessionUri().toString();
                            //String uri_photo = taskSnapshot.getDownloadUrl().toString();
                            reference.getRef().child("url_profile_photo").setValue(uri_photo);
                            reference.getRef().child("full_name").setValue(full_name.getText().toString());
                            reference.getRef().child("hobby").setValue(hobby.getText().toString());
                        }
                    }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Intent intent = new Intent(RegisterNextActivity.this, RegisterSuccessActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPhoto();
            }
        });
    }

    public void findPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, photo_max);
    }

    // auto generate from onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == photo_max && resultCode == RESULT_OK && data != null && data.getData() != null){
            photo_location = data.getData();
            Picasso.with(this).load(photo_location).centerCrop().fit().into(photo_register);
        }
    }

    String getFileExtention(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void getUsername(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }

}
