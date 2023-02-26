package com.project.service_provider.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.project.service_provider.R;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class admin_transport extends AppCompatActivity {
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private AlertDialog progressAlertDialog;
    TextInputLayout name, Vnumber, mobileNumber, DLnumber, location;
    Button srcbtn;
    ImageView imgupl, GeoL;
    Uri imageUri;
    Bitmap bitmap;

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transport);
        name = findViewById(R.id.driverName);
        Vnumber = findViewById(R.id.Vnumber);
        mobileNumber = findViewById(R.id.Mnumber);
        DLnumber = findViewById(R.id.DlNumber);
        location = findViewById(R.id.location);
        imgupl = findViewById(R.id.imguploadT);
        srcbtn = findViewById(R.id.trsubmitBtn);
        GeoL = findViewById(R.id.geoLocation);


        GeoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if (ContextCompat.checkSelfPermission(admin_transport.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(admin_transport.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
                } else {
                    // permission has already been granted
                    getlocation();
                }


            }
        });

        imgupl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image File"), 1);

            }
        });

        srcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Name = name.getEditText().getText().toString();
                String VihicleNumber = Vnumber.getEditText().getText().toString();
                String MobileNumber = mobileNumber.getEditText().getText().toString();
                String DrivingLNumber = DLnumber.getEditText().getText().toString();
                String Location = location.getEditText().getText().toString().trim();




                if (TextUtils.isEmpty(Name)) {
                    name.setError("Name cannot be empty");
                    name.requestFocus();
                } else if (TextUtils.isEmpty(VihicleNumber)) {
                    Vnumber.setError("VihicleNumber cannot be empty");
                    Vnumber.requestFocus();
                } else if (TextUtils.isEmpty(MobileNumber)) {
                    mobileNumber.setError("MobileNumber cannot be empty");
                    mobileNumber.requestFocus();
                } else if (TextUtils.isEmpty(DrivingLNumber)) {
                    DLnumber.setError("DrivingLNumber cannot be empty");
                    DLnumber.requestFocus();

                } else if (TextUtils.isEmpty(Location)) {
                    location.setError("Location cannot be empty");
                    location.requestFocus();

                } else {


                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference uploder = storage.getReference("Image1" + new Random().nextInt(50));

                    uploder.putFile(imageUri)
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    if (progressAlertDialog == null) {
                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(admin_transport.this);
                                        builder2.setTitle("Alert !");
                                        builder2.setMessage("Uploaded: 0%");
                                        builder2.setPositiveButton(
                                                "Ok",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });

                                        progressAlertDialog = builder2.create();
                                        progressAlertDialog.show();
                                    }

                                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                    progressAlertDialog.setMessage("Uploaded: " + (int) progress + "%");
                                }
                            })
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    uploder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                            FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                                            DatabaseReference root = rootNode.getReference("Transport");
                                            transportHelper helper = new transportHelper(Name, VihicleNumber, MobileNumber, DrivingLNumber, Location,currentuser, uri.toString());

                                            String key = root.push().getKey();
                                            root.child(key).setValue(helper);

                                            name.getEditText().setText("");
                                            Vnumber.getEditText().setText("");
                                            mobileNumber.getEditText().setText("");
                                            DLnumber.getEditText().setText("");
                                            location.getEditText().setText("");
                                            imgupl.setImageResource(R.drawable.add_img_24);
                                            Toast.makeText(admin_transport.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            });
                }
            }
        });
    }


    private void getlocation() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    TextInputLayout Location;
                    Location = findViewById(R.id.location);
                    Location.getEditText().setText("");
                    // location found
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    // update your UI or save the location to a database
                    try {
                        Geocoder geocoder = new Geocoder(admin_transport.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        String address = addresses.get(0).getAddressLine(0);
                        Location.getEditText().setText(address);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    // location not found, try again or show an error message
                    Toast.makeText(admin_transport.this, "location not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    @SuppressLint("MissingPermission")
//    private void getlocation() {
//
//        try {
////            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
////            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,admin_transport.this);
//            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
//            } else {
//                // Prompt the user to enable GPS
//                Toast.makeText(admin_transport.this, "Enable GPS", Toast.LENGTH_LONG).show();
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode ==1 && resultCode == RESULT_OK ){

            imageUri = data.getData();

            try {
                InputStream inputStream  = admin_transport.this.getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgupl.setImageBitmap(bitmap);

            }catch (Exception e)
            {

            }



        }
    }

//    LocationListener locationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(@NonNull Location location) {
//            TextInputLayout Location;
//            Location = findViewById(R.id.location);
//            Location.getEditText().setText("");
//            Toast.makeText(admin_transport.this, "" + location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_LONG).show();
//
//            try {
//                Geocoder geocoder = new Geocoder(admin_transport.this, Locale.getDefault());
//                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                String address = addresses.get(0).getAddressLine(0);
//                Location.getEditText().setText(address);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {}
//
//        @Override
//        public void onProviderEnabled(@NonNull String provider) {}
//
//        @Override
//        public void onProviderDisabled(@NonNull String provider) {}
//    };

    //old code
    // Don't forget to remove the location updates when your activity is destroyed
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        locationManager.removeUpdates(locationListener);
//    }



    // Call this method to start listening for location updates
    private void startLocationUpdates() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Handle the new location
                locationManager.removeUpdates(locationListener); // Stop receiving location updates
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // Handle location provider status changes
            }

            @Override
            public void onProviderEnabled(String provider) {
                // Handle location provider enabled
            }

            @Override
            public void onProviderDisabled(String provider) {
                // Handle location provider disabled
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

}