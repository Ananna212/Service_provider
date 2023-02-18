package com.project.service_provider;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailsInfo extends AppCompatActivity {
    String name,designation,aera_of_study,email,phone,adress,picurl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info);
        ImageView fetchimag = findViewById(R.id.fetchimag);
        TextView Name = findViewById(R.id.fetchName);
        TextView desig = findViewById(R.id.fetshDesignation);
        TextView sfa = findViewById(R.id.fetchAofSrudy);
        TextView  Email = findViewById(R.id.festhEmail);
        TextView  mobile = findViewById(R.id.fetchMobile);
        TextView address = findViewById(R.id.fetchAdress);

         String name = getIntent().getExtras().getString("name","defaultKey");
        String designation = getIntent().getExtras().getString("designation","defaultKey");
        String aera_of_study = getIntent().getExtras().getString("area_of_study","defaultKey");
        String email = getIntent().getExtras().getString("email","defaultKey");
        String phone = getIntent().getExtras().getString("phone","defaultKey");
        String adress = getIntent().getExtras().getString("address","defaultKey");
        String picurl = getIntent().getExtras().getString("picurl","defaultKey");





        Name.setText(name);
        desig.setText(designation);
        sfa.setText(aera_of_study);
        Email.setText(email);
        mobile.setText(phone);
        address.setText(adress);
        Glide.with(detailsInfo.this).load(picurl).into(fetchimag);

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(detailsInfo.this,MainActivity.class);
        intent.putExtra("key","Teacher");
        startActivity(intent);
         finish();
    }
}