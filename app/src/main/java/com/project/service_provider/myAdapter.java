package com.project.service_provider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myAdapter extends FirebaseRecyclerAdapter<modelAdapter,myAdapter.myviewholder>{





    public myAdapter(@NonNull FirebaseRecyclerOptions<modelAdapter> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull modelAdapter model) {



        holder.Name.setText(model.getName());
        holder.designation.setText(model.getDesignation());

        Glide.with(holder.imgl.getContext()).load(model.getPicurl()).into(holder.imgl);


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
        View viewLayout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgl = (ImageView) itemView.findViewById(R.id.imagep);
         Name = itemView.findViewById(R.id.card_name);
         designation = itemView.findViewById(R.id.card_d);
         viewLayout = itemView.findViewById(R.id.cardLayout);


        }
    }

}
