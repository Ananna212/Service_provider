package com.project.service_provider.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.project.service_provider.R;

public class admin_home extends AppCompatActivity {
    LinearLayout Ed,tr,md,st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Ed = findViewById(R.id.EducationBtn);
        tr = findViewById(R.id.TransportBtn);
        md = findViewById(R.id.infoAdd);
        st = findViewById(R.id.statistics);

        md.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_home.this,admin_Doctor.class);
                startActivity(intent);
            }
        });

        Ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_home.this,admin_Education.class);
                startActivity(intent);
            }
        });


        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trintent = new Intent(admin_home.this,admin_transport.class);
                startActivity(trintent);
            }
        });

        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_home.this,Statistics.class);
                startActivity(intent);
            }
        });
    }
}