package com.project.service_provider;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Signin_activity extends AppCompatActivity  {

    EditText username, email, address, pass, confirmpass;
    Button Register, logedIn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        pass = findViewById(R.id.pass);
        confirmpass = findViewById(R.id.confirmpass);
        Register = findViewById(R.id.Register);
        logedIn = findViewById(R.id.logedIn);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username = username.getText().toString();
                String Email = email.getText().toString().trim();
                String Address = address.getText().toString();
                String Pass = pass.getText().toString();
                String Confirmpass = confirmpass.getText().toString();
                System.out.println("Output Email "+Email);


               firebaseAuth.createUserWithEmailAndPassword(Email,Pass)
                       .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                   @Override
                   public void onSuccess(AuthResult authResult) {
                       firebaseFirestore.collection("users")
                               .document(FirebaseAuth.getInstance().getUid())
                               .set(new model(Username,Email,Address,Pass,Confirmpass));
                       Toast.makeText(Signin_activity.this, "Registration successfull!", Toast.LENGTH_LONG).show();
                       //Intent ok = new Intent(Signin_activity.this, Login_activity.class);
                       //startActivities(ok);

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(Signin_activity.this, "Registration failed!", Toast.LENGTH_LONG).show();

                   }
               });

            }
        });

        logedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Signin_activity.this, Login_activity.class);
                startActivity(myIntent);
                finish();
            }
        });

    }
}