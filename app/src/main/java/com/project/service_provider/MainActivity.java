package com.project.service_provider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.project.service_provider.admin.TransportmyAdapter;
import com.project.service_provider.admin.transportAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView recview;
    Button srcbtn;
    EditText edSec;
    FirebaseRecyclerAdapter adapter;
   SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recview = findViewById(R.id.recview);
        searchView = findViewById(R.id.searchA);
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();

        String data = getIntent().getExtras().getString("key","Teacher");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                if(data.equals("Teacher")){
                    filterList(newText);
                }
                else if(data.equals("Transport")){
                    filterListT(newText);
                }
                else if(data.equals("Medical")){
                    filterListTM(newText);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(data.equals("Teacher")){
                    filterList(newText);
                }
                else if(data.equals("Transport")){
                    filterListT(newText);
                }
                else if(data.equals("Medical")){
                    filterListTM(newText);
                }
                return false;
            }
        });

        if(data.equals("Teacher")){
            recview.setLayoutManager((new LinearLayoutManager(MainActivity.this)));
            FirebaseRecyclerOptions<modelAdapter> options =
                    new FirebaseRecyclerOptions.Builder<modelAdapter>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Teacher"), modelAdapter.class)
                            .build();

            adapter = new myAdapter(options);
            recview.setAdapter(adapter);

        }else if(data.equals("Transport")){
            recview.setLayoutManager((new LinearLayoutManager(MainActivity.this)));
            FirebaseRecyclerOptions<transportAdapter> toptions =
                    new FirebaseRecyclerOptions.Builder<transportAdapter>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Transport"), transportAdapter.class)
                            .build();

            adapter = new TransportmyAdapter(toptions);
            recview.setAdapter(adapter);
        }else if(data.equals("Medical")){
            recview.setLayoutManager((new LinearLayoutManager(MainActivity.this)));
            FirebaseRecyclerOptions<medicalAdapter> toptions =
                    new FirebaseRecyclerOptions.Builder<medicalAdapter>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Medical"), medicalAdapter.class)
                            .build();

            adapter = new medicalmyAdapter(toptions);
            recview.setAdapter(adapter);

        }



    }

    private void filterListTM(String newText) {

        FirebaseRecyclerOptions<medicalAdapter> toptions =
                new FirebaseRecyclerOptions.Builder<medicalAdapter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Medical").orderByChild("name").startAt(newText), medicalAdapter.class)
                        .build();
        adapter = new medicalmyAdapter(toptions);
        adapter.startListening();
        recview.setAdapter(adapter);
    }

    private void filterListT(String newText) {
        FirebaseRecyclerOptions<transportAdapter> toptions =
                new FirebaseRecyclerOptions.Builder<transportAdapter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Transport").orderByChild("name").startAt(newText), transportAdapter.class)
                        .build();
        adapter = new TransportmyAdapter(toptions);
        adapter.startListening();
        recview.setAdapter(adapter);
    }

    private void filterList(String newText) {

        FirebaseRecyclerOptions<modelAdapter> options =
                new FirebaseRecyclerOptions.Builder<modelAdapter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Teacher").orderByChild("name").startAt(newText), modelAdapter.class)
                        .build();
        adapter = new myAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }



}