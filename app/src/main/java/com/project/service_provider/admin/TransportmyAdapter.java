package com.project.service_provider.admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.service_provider.R;
import com.project.service_provider.detailsInfo;
import com.project.service_provider.modelAdapter;
import com.project.service_provider.myAdapter;

public class TransportmyAdapter extends FirebaseRecyclerAdapter<transportAdapter, TransportmyAdapter.myviewholder> {

    public TransportmyAdapter(@NonNull FirebaseRecyclerOptions<transportAdapter> toptions) {
        super(toptions);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull transportAdapter model) {


        holder.Name.setText(model.getName());
        holder.LicenceNumber.setText(model.getVihicleNumber());
        holder.Adress.setText(model.getLocation());

        Glide.with(holder.imgl.getContext()).load(model.getPic()).into(holder.imgl);

        // delete button
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.imgl.getContext());
                builder.setTitle("Delete Post");
                builder.setMessage("Delete....!");
                builder.setPositiveButton("Yes",(dialog, i) -> {
                    String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    FirebaseDatabase.getInstance().getReference().child("Transport")
                            .orderByChild("curentUID")
                            .equalTo(currentUserID)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                    Log.d(TAG, "onDataChange called");
                                    for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
//                                        Log.d(TAG, "childSnapshot: " + childSnapshot.getValue());
                                        System.out.println("childSnapshot: " + childSnapshot.getValue());
                                        String key = childSnapshot.getKey();
                                        if (key.equals(getRef(position).getKey())) {
                                            // Remove the child node
                                            childSnapshot.getRef().removeValue();
//                                            Log.d(, "Data deleted successfully");
                                            break;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    // Handle the error

//                                    Log.e(myAdapter, "Error deleting data", databaseError.toException());
                                    System.out.println("Error deleting data"+ databaseError.toException());
                                }
                            });
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });

                builder.show();


            }
        });


        holder.viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, TransportDetailsInfo.class);
                intent.putExtra("name", model.getName());
                intent.putExtra("VihicleNumber", model.getVihicleNumber());
                intent.putExtra("Address", model.getLocation());
                intent.putExtra("DrivingLicence", model.getDrivingLnumber());
                intent.putExtra("phone", model.getMobileNumber());
                intent.putExtra("picurl", model.getPic());
                context.startActivity(intent);


                // finish the current activity
                ((Activity) context).finish();

            }
        });

    }




    @NonNull
    @Override
    public TransportmyAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transportsinglerow,parent,false);
        return new TransportmyAdapter.myviewholder(view);
    }



    public class myviewholder extends RecyclerView.ViewHolder{


        ImageView imgl;
        TextView Name,LicenceNumber,Adress;
        View viewLayout;
        Button Delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgl = (ImageView) itemView.findViewById(R.id.timagep);
            Name = itemView.findViewById(R.id.tcard_name);
            LicenceNumber = itemView.findViewById(R.id.tcard_n);
            Adress = itemView.findViewById(R.id.tLocation);
            viewLayout = itemView.findViewById(R.id.cardLayout);
            Delete = itemView.findViewById(R.id.deletet);


        }
    }

}
