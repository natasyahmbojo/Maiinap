package com.example.maiinap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView tv_EMAIL, tv_USERNAME, tv_jk, tv_SEWA, tv_PASSWORD, tv_presentase;
    DBHelper db;
    Button contact;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new DBHelper(this);
        Intent intent_terima = getIntent();
        String Terima_EMAIL = intent_terima.getStringExtra(MainActivity.EMAIL);
        String Terima_USERNAME = intent_terima.getStringExtra(MainActivity.USERNAME);
        String Terima_jk = intent_terima.getStringExtra(MainActivity.JK);
        String Terima_SEWA = intent_terima.getStringExtra(MainActivity.SEWA);
        String Terima_PASSWORD = intent_terima.getStringExtra(MainActivity.PASSWORD);
        String Terima_presentase = intent_terima.getStringExtra(MainActivity.PRESENTASE);


        tv_USERNAME = (TextView) findViewById(R.id.t_USERNAME);
        tv_EMAIL = (TextView) findViewById(R.id.t_EMAIL);
        tv_jk = (TextView) findViewById(R.id.t_jk);
        tv_PASSWORD = (TextView) findViewById(R.id.t_PASSWORD);
        tv_SEWA = (TextView) findViewById(R.id.t_SEWA);
        tv_presentase = (TextView) findViewById(R.id.t_presentase);
        contact = findViewById(R.id.contactbtn);

        tv_USERNAME.setText(Terima_EMAIL);
        tv_EMAIL.setText(Terima_USERNAME);
        tv_jk.setText(Terima_jk);
        tv_PASSWORD.setText(Terima_PASSWORD);
        tv_SEWA.setText(Terima_SEWA);
        tv_presentase.setText(Terima_presentase);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, ContactList.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        Toast messageText = Toast.makeText(getApplicationContext(), "Membuka Activity..", Toast.LENGTH_SHORT);
        messageText.show();
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Toast messageText = Toast.makeText(getApplicationContext(), "Membuka Kembali Activity..", Toast.LENGTH_SHORT);
        messageText.show();
        super.onRestart();
    }


    @Override
    protected void onStop() {
        Toast messageText = Toast.makeText(getApplicationContext(), "Menutup Activity..", Toast.LENGTH_SHORT);
        messageText.show();
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        Toast messageText = Toast.makeText(getApplicationContext(), "Menutup Penuh Activity..", Toast.LENGTH_SHORT);
        messageText.show();
        super.onDestroy();
    }
}