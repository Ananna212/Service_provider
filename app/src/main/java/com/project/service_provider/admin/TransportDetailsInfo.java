package com.project.service_provider.admin;

import androidx.annotation.NonNull;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.project.service_provider.MainActivity;
import com.project.service_provider.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class TransportDetailsInfo extends AppCompatActivity {

    LinearLayout firstL, scndL,thridL;
    private String verificationId;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    EditText editTextCountryCode, editTextPhone,fammount,OTP;
    Button buttonContinue,buttonVerify,EndButton;
    //dialog
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



        //Payment Dialog
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new Dialog(TransportDetailsInfo.this);
                myDialog.setContentView(R.layout.activity_paymentsystem);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                mAuth = FirebaseAuth.getInstance();
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                // new code

                firstL =(LinearLayout) myDialog.findViewById(R.id.firstpase);
                scndL = (LinearLayout) myDialog.findViewById(R.id.scndpase);
                thridL =  (LinearLayout)myDialog.findViewById(R.id.thridpase);

              //first step part
                fammount =  (EditText) myDialog.findViewById(R.id.ammount);
                editTextCountryCode = (EditText) myDialog.findViewById(R.id.editTextCountryCode);
                editTextPhone = (EditText) myDialog.findViewById(R.id.editTextPhone);


                buttonContinue =(Button) myDialog.findViewById(R.id.buttonContinue);
                buttonVerify =  (Button)myDialog.findViewById(R.id.buttonVerify);
                EndButton = (Button) myDialog.findViewById(R.id.done);
                //otp part
                progressBar = (ProgressBar) myDialog.findViewById(R.id.progressbar);
                OTP =(EditText) myDialog.findViewById(R.id.editTextCode);

                buttonContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String code = editTextCountryCode.getText().toString().trim();
                        String number = editTextPhone.getText().toString().trim();

                        if (number.isEmpty() || number.length() < 10) {
                            editTextPhone.setError("Valid number is required");
                            editTextPhone.requestFocus();
                            return;
                        }

                         String phoneNumber = code + number;
                        sendVerificationCode(phoneNumber);

                        firstL.setVisibility(View.GONE);
                        scndL.setVisibility(View.VISIBLE);
                    }
                });

                buttonVerify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String code = OTP.getText().toString().trim();

                        if (code.isEmpty() || code.length() < 6) {

                            OTP.setError("Enter code...");
                            OTP.requestFocus();
                            return;
                        }
                        verifyCode(code);
                    }
                });


                EndButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });


                //new code end
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

    // FIREBASE PHONE AUTHENTICATION
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            scndL.setVisibility(View.GONE);
                            thridL.setVisibility(View.VISIBLE);
//                            Intent intent = new Intent(VerifyPhoneActivity.this, ProfileActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                            startActivity(intent);

                        } else {
                            Toast.makeText(TransportDetailsInfo.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void sendVerificationCode(String number) {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60L,
                TimeUnit.SECONDS,
                TransportDetailsInfo.this,
                mCallBack
        );

        progressBar.setVisibility(View.GONE);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                OTP.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(TransportDetailsInfo.this, e.getMessage(), Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }
    };
}