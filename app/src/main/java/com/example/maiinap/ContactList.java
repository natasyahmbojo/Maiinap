package com.example.maiinap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {
    DBHelper db;
    RecyclerView contactList;
    FloatingActionButton add_button;
    ArrayList<String> contact_username, contact_password, contact_sewa, contact_email;
    Adapter contactAdapter;
    ImageView emptyImg;
    TextView noData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Contact List");
        }

        db = new DBHelper(this);
        contactList = findViewById(R.id.contactList);
        add_button = findViewById(R.id.addBtn);
        emptyImg = findViewById(R.id.emptyImg);
        noData = findViewById(R.id.noData);

        contact_username = new ArrayList<>();
        contact_password = new ArrayList<>();
        contact_sewa = new ArrayList<>();
        contact_email = new ArrayList<>();

        storeDataInArrays();

        contactAdapter = new Adapter(ContactList.this,this, contact_sewa, contact_username, contact_password,
                contact_email);
        contactList.setAdapter(contactAdapter);
        contactList.setLayoutManager(new LinearLayoutManager(ContactList.this));

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactList.this, CreateContact.class);
                startActivity(intent);
            }
        });
    }

    void storeDataInArrays(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            emptyImg.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                contact_username.add(cursor.getString(0));
                contact_password.add(cursor.getString(1));
                contact_sewa.add(cursor.getString(2));
                contact_email.add(cursor.getString(3));
            }
            emptyImg.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        }
    }
}