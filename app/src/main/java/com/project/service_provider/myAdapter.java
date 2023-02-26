package com.project.service_provider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class myAdapter extends FirebaseRecyclerAdapter<modelAdapter,myAdapter.myviewholder>{





    public myAdapter(@NonNull FirebaseRecyclerOptions<modelAdapter> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull modelAdapter model) {



        holder.Name.setText(model.getName());
        holder.designation.setText(model.getDesignation());

        Glide.with(holder.imgl.getContext()).load(model.getPicurl()).into(holder.imgl);

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.imgl.getContext());
                builder.setTitle("Delete Post");
                builder.setMessage("Delete....!");
                builder.setPositiveButton("Yes",(dialog, i) -> {
                    String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    FirebaseDatabase.getInstance().getReference().child("Teacher")
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
                Intent intent = new Intent(context, detailsInfo.class);
                intent.putExtra("name", model.getName());
                intent.putExtra("designation", model.getDesignation());
                intent.putExtra("area_of_study", model.getAera_of_study());
                intent.putExtra("email", model.getEmail());
                intent.putExtra("phone", model.getPhone());
                intent.putExtra("address", model.getAdress());
                intent.putExtra("picurl", model.getPicurl());

                context.startActivity(intent);

                // finish the current activity
                ((Activity) context).finish();
            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myviewholder(view);
    }



    public class myviewholder extends RecyclerView.ViewHolder{


        ImageView imgl;
        TextView Name,designation;
        Button Delete;
        View viewLayout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgl = (ImageView) itemView.findViewById(R.id.imagep);
         Name = itemView.findViewById(R.id.card_name);
         designation = itemView.findViewById(R.id.card_d);
         viewLayout = itemView.findViewById(R.id.cardLayout);
            Delete = itemView.findViewById(R.id.delete);


        }
    }

}
