package com.project.service_provider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.project.service_provider.admin.TransportDetailsInfo;

public class Activity_doctor_profile extends AppCompatActivity {

    MaterialTextView Name,SpArea,adress,number,email;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);


        img = findViewById(R.id.imageView3);
        Name = findViewById(R.id.doctor_name);
        SpArea = findViewById(R.id.doctor_specialite);
        adress = findViewById(R.id.doctor_address);
        number = findViewById(R.id.doctor_phone);
        email = findViewById(R.id.doctor_email);



        String name = getIntent().getExtras().getString("name","defaultKey");
        String spArea = getIntent().getExtras().getString("specialistArea","defaultKey");
        String Address = getIntent().getExtras().getString("Address","defaultKey");
        String phone = getIntent().getExtras().getString("phone","defaultKey");
        String picurl = getIntent().getExtras().getString("picurl","defaultKey");
        String Email = getIntent().getExtras().getString("Email","defaultKey");





        SpArea.setText(spArea);
        Name.setText(name);
        email.setText(Email);
        adress.setText(Address);
        number.setText(phone);
        Glide.with(Activity_doctor_profile.this).load(picurl).into(img);


    }

    public void onBackPressed() {
        Intent intent = new Intent(Activity_doctor_profile.this,MainActivity.class);
        intent.putExtra("key","Medical");
        startActivity(intent);
        finish();
    }
}