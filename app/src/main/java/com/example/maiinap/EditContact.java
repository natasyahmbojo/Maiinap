package com.example.maiinap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {


    DBHelper db;
    EditText username, password, email,sewa;
    Button updateBtn, deleteBtn;
    String id, getUsername, getPassword, getEmail, getSewa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        db = new DBHelper(this);
        username = findViewById(R.id.editNama);
        password = findViewById(R.id.editPhone);
        email = findViewById(R.id.editEmail);
        sewa = findViewById(R.id.editSewa);
        updateBtn = findViewById(R.id.updateContact);
        deleteBtn = findViewById(R.id.deleteBtn);

        getAndSetIntentData();
        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(getUsername);
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                db = new DBHelper(EditContact.this);
                getUsername = username.getText().toString().trim();
                getPassword = password.getText().toString().trim();
                getEmail = email.getText().toString().trim();
                getSewa = sewa.getText().toString().trim();
                db.updateData(id, getUsername, getPassword, getEmail,getPassword);

                Intent intent = new Intent(EditContact.this, ContactList.class);
                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("number") && getIntent().hasExtra("email")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            getUsername = getIntent().getStringExtra("name");
            getPassword = getIntent().getStringExtra("number");
            getEmail = getIntent().getStringExtra("email");
            getSewa = getIntent().getStringExtra("sewa");

            //Setting Intent Data
            username.setText(getUsername);
            password.setText(getPassword);
            email.setText(getEmail);
            sewa.setText(getSewa);
            Log.d("stev", username+" "+password+" "+email+" "+sewa);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + getUsername + " ?");
        builder.setMessage("Are you sure you want to delete " + getUsername + " contact?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db = new DBHelper(EditContact.this);
                db.deleteOneRow(id);
                Intent intent = new Intent(EditContact.this, ContactList.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}