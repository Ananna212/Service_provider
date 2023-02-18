package com.example.licencesearching;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myAdapter extends FirebaseRecyclerAdapter<model,myAdapter.myviewholder>{





    public myAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {



        holder.nametext.setText(model.getA_Shop_Owner_Name());
        holder.businessType.setText(model.getB_Bussines_Type());
        holder.shop_name.setText(model.getC_Shop_Name());
        holder.adress.setText(model.getD_Shop_Address());
        holder.division.setText(model.getE_Divison());
        holder.licence_number.setText(model.getF_Licence_Number().replace("s","/").replace("d","-"));
        holder.nid.setText(model.getG_NID());
        holder.phone_number.setText(model.getH_Phonne_Number());
        holder.licenceup_date.setText(model.getI_Last_Licence_update());
        Glide.with(holder.imgl.getContext()).load(model.getK_picurl()).into(holder.imgl);


        holder.viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frg_cnt,new info_show_frg( model.getA_Shop_Owner_Name(),model.getB_Bussines_Type(),model.getC_Shop_Name(),model.getD_Shop_Address(),model.getE_Divison(),model.getF_Licence_Number().replaceFirst("_","/"),model.getG_NID(),model.getH_Phonne_Number(),model.getI_Last_Licence_update(),model.getK_picurl())).addToBackStack(null).commit();
            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
       return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{


        ImageView imgl;
        TextView nametext,businessType,shop_name,adress,division,licence_number,nid,phone_number,licenceup_date;
        View viewLayout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgl = (ImageView) itemView.findViewById(R.id.imagel);
            nametext = itemView.findViewById(R.id.fetchName);
            businessType = itemView.findViewById(R.id.fetchbusnesstype);
            shop_name = itemView.findViewById(R.id.fetchShopname);
            adress = itemView.findViewById(R.id.fetchAdress);
            division = itemView.findViewById(R.id.fetchDivision);
            licence_number = itemView.findViewById(R.id.fetchLicence);
            nid = itemView.findViewById(R.id.fetchNID);
            phone_number = itemView.findViewById(R.id.fetchNumber);
            licenceup_date = itemView.findViewById(R.id.fetchLast);
            viewLayout = itemView.findViewById(R.id.SpcLayout);



        }
    }

}
