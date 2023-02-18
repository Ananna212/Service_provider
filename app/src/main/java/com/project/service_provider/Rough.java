package com.project.service_provider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class Rough extends AppCompatActivity {

    CardView Education,Transport,medical;
    TextView Profile,logout;
    LinearLayout profiled;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rough);
        Education = findViewById(R.id.education);
        Transport = findViewById(R.id.TransPortcard);
        medical = findViewById(R.id.medical);
        Profile = findViewById(R.id.profile);
        logout= findViewById(R.id.LogOut);
        profiled = findViewById(R.id.profileClick);

        profiled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rough.this, User_profiles.class);
                startActivity(intent);
            }
        });

        FirebaseFirestore dbroot = FirebaseFirestore.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println("check_id: "+currentuser);
        DocumentReference documentReference = dbroot.collection("users").document(currentuser);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Profile.setText(documentSnapshot.getString("username"));
                }else Toast.makeText(Rough.this, "User name not found", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Rough.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });

        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rough.this, MainActivity.class);
                intent.putExtra("key","Medical");
                startActivity(intent);
            }
        });



        Education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rough.this, MainActivity.class);
                intent.putExtra("key","Teacher");
                startActivity(intent);
            }
        });

        Transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rough.this, MainActivity.class);
                intent.putExtra("key","Transport");
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rough.this,Login_activity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}