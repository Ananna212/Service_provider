package com.project.service_provider.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.service_provider.MainActivity;
import com.project.service_provider.R;
import com.project.service_provider.detailsInfo;

public class TransportDetailsInfo extends AppCompatActivity {

    TextView Name,vnumber,adress,drivincLicence,number;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_details_info);
        Name = findViewById(R.id.tfetchName);
        vnumber = findViewById(R.id.tfetshVnumber);
        adress = findViewById(R.id.tfetchAdress);
        drivincLicence = findViewById(R.id.tfesthDlicence);
        number = findViewById(R.id.tfetchMobile);
        img = findViewById(R.id.tfetchimag);


        String name = getIntent().getExtras().getString("name","defaultKey");
        String VihicleNumber = getIntent().getExtras().getString("VihicleNumber","defaultKey");
        String Address = getIntent().getExtras().getString("Address","defaultKey");
        String DrivingLicence = getIntent().getExtras().getString("DrivingLicence","defaultKey");
        String phone = getIntent().getExtras().getString("phone","defaultKey");
        String picurl = getIntent().getExtras().getString("picurl","defaultKey");

        System.out.println("Tname: "+name);
        System.out.println("TVc: "+VihicleNumber);
        System.out.println("TAdre: "+Address);
        System.out.println("Tdv: "+DrivingLicence);
        System.out.println("Tphone: "+phone);




        Name.setText(name);
        vnumber.setText(VihicleNumber);
        adress.setText(Address);
        drivincLicence.setText(DrivingLicence);
        number.setText(phone);
        Glide.with(TransportDetailsInfo.this).load(picurl).into(img);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TransportDetailsInfo.this, MainActivity.class);
        intent.putExtra("key", "Transport");
        startActivity(intent);
        finish();
    }
}