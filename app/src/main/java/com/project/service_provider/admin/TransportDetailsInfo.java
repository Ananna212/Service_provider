package com.project.service_provider.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.service_provider.Activity_doctor_profile;
import com.project.service_provider.MainActivity;
import com.project.service_provider.R;
import com.project.service_provider.detailsInfo;
import com.project.service_provider.paymentsystem;

public class TransportDetailsInfo extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    TextView Name,vnumber,adress,drivincLicence,number,txtclose;
    ImageView img,call,location,payment;
    Dialog myDialog;
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
        call = findViewById(R.id.imageViewcall);
        location = findViewById(R.id.imageViewlocation);
        payment = findViewById(R.id.PAYMENT);


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

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new Dialog(TransportDetailsInfo.this);
                myDialog.setContentView(R.layout.activity_paymentsystem);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PhoneNumber = number.getText().toString();
                callNumber(PhoneNumber);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = adress.getText().toString();
                // Create an intent with the address as a query parameter
                Uri uri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(Intent.createChooser(intent, "Open with"));
            }
        });


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

    private void callNumber(String number) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(TransportDetailsInfo.this, "Please grant the call permission to proceed.", Toast.LENGTH_LONG).show();
            return;
        }
        startActivityForResult(callIntent, REQUEST_CALL);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CALL) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(TransportDetailsInfo.this, "Call sent successfully.", Toast.LENGTH_LONG).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(TransportDetailsInfo.this, "Call failed. Please try again.", Toast.LENGTH_LONG).show();
            }
        }
    }
}