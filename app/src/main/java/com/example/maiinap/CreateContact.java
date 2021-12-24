package com.example.maiinap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity {


    DBHelper db;
    EditText username, password, email,sewa;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        db = new DBHelper(this);
        username = findViewById(R.id.addNama);
        password = findViewById(R.id.addPhone);
        email = findViewById(R.id.addEmail);
        sewa = findViewById(R.id.addSewa);
        saveBtn = findViewById(R.id.addContact);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = username.getText().toString();
                String getPassword = password.getText().toString();
                String getEmail = email.getText().toString();
                String getSewa = sewa.getText().toString();

                if (getUsername.matches("") || getPassword.matches("") || getEmail.matches("")|| getSewa.matches("")) {
                    Toast.makeText(CreateContact.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(DBHelper.row_user, getUsername);
                    values.put(DBHelper.row_pass, getPassword);
                    values.put(DBHelper.row_emil, getEmail);
                    values.put(DBHelper.row_swa, getSewa);
                    DBHelper.insertContact(values);

                    Intent intent = new Intent(CreateContact.this, ContactList.class);
                    Toast.makeText(CreateContact.this, "Data successfully saved.", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Add Contact");
        }
    }
}