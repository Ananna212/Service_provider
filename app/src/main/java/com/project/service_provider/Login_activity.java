package com.project.service_provider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_activity extends AppCompatActivity {
    EditText userMail,userPass;
    Button Login, signup;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userMail = findViewById(R.id.email);
        userPass = findViewById(R.id.passwords);
        Login = findViewById(R.id.signIn);
        signup = findViewById(R.id.signUp);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userMail.getText().toString().trim();
                String pass = userPass.getText().toString();

                String emailRegex = "^(?=^[A-Za-z0-9._%+-]+@)(?=.*gmail\\.com$).+";
                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(email);

                String adminRegex = "^(?=^[A-Za-z0-9._%+-]+@)(?=.*admin\\.com$).+";
                Pattern adminpattern = Pattern.compile(adminRegex);
                Matcher adminatcher = adminpattern.matcher(email);

                if(TextUtils.isEmpty(email)){
                    userMail.setError("Email can't empty");
                    userMail.requestFocus();
                }else if(TextUtils.isEmpty(pass)){
                    userPass.setError("Password Can't Empty");
                    userPass.requestFocus();
                }else {

                    if(matcher.matches()) {
                        mAuth = FirebaseAuth.getInstance();

                        mAuth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {


                                        Intent myintent = new Intent(Login_activity.this, Rough.class);
                                        startActivity(myintent);

                                        Toast.makeText(Login_activity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(Login_activity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    } else if (adminatcher.matches()) {
                        mAuth = FirebaseAuth.getInstance();

                        mAuth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {


                                        Intent myintent = new Intent(Login_activity.this, com.project.service_provider.admin.admin_home.class);
                                        startActivity(myintent);

                                        Toast.makeText(Login_activity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(Login_activity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                    }
                                });
                        
                    }
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Login_activity.this, Signin_activity.class);
                startActivity(myIntent);
            }
        });
    }
}