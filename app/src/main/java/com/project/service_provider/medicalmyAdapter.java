package com.project.service_provider;

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
import com.project.service_provider.admin.TransportDetailsInfo;
import com.project.service_provider.admin.TransportmyAdapter;
import com.project.service_provider.admin.transportAdapter;

public class medicalmyAdapter extends FirebaseRecyclerAdapter<medicalAdapter, medicalmyAdapter.myviewholder> {

    public medicalmyAdapter(@NonNull FirebaseRecyclerOptions<medicalAdapter> toptions) {
        super(toptions);
    }

    @Override
    protected void onBindViewHolder(@NonNull medicalmyAdapter.myviewholder holder, int position, @NonNull medicalAdapter model) {


        holder.drName.setText(model.getName());
        holder.SpArea.setText(model.getSpecialist());
//        System.out.println("Asche: "+model.getDrName());
//        System.out.println("Asche: "+model.getSpecialistArea());


        Glide.with(holder.imgl.getContext()).load(model.getPic()).into(holder.imgl);


        // delete part
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.imgl.getContext());
                builder.setTitle("Delete Post");
                builder.setMessage("Delete....!");
                builder.setPositiveButton("Yes",(dialog, i) -> {
                    String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    FirebaseDatabase.getInstance().getReference().child("Medical")
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
                Intent intent = new Intent(context, Activity_doctor_profile.class);
                intent.putExtra("name", model.getName());
                intent.putExtra("specialistArea", model.getSpecialist());
                intent.putExtra("Address", model.getAdress());
                intent.putExtra("phone", model.getPhone());
                intent.putExtra("picurl", model.getPic());
                intent.putExtra("Email", model.getEmail());
                context.startActivity(intent);

                // finish the current activity
                ((Activity) context).finish();

//                AppCompatActivity activity=(AppCompatActivity)view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(myAdapter.this,new detailsInfo(model.getName(),model.getDesignation(),model.getAera_of_study(),model.getEmail(),model.getPhone(),model.getAdress(),model.getPicurl())).addToBackStack(null).commit();
            }
        });

    }




    @NonNull
    @Override
    public medicalmyAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_single_profile,parent,false);
        return new medicalmyAdapter.myviewholder(view);
    }



    public class myviewholder extends RecyclerView.ViewHolder{


        ImageView imgl;
        TextView drName,SpArea;
        View viewLayout;
        Button Delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgl = (ImageView) itemView.findViewById(R.id.imaged);
            drName = itemView.findViewById(R.id.card_nameD);
            SpArea = itemView.findViewById(R.id.card_dD);
//            Adress = itemView.findViewById(R.id.tLocation);
            viewLayout = itemView.findViewById(R.id.cardLayout);
            Delete = itemView.findViewById(R.id.deleted);


        }
    }

}