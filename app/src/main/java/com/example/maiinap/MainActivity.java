package com.example.maiinap;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EMAIL = "Email";
    public static final String USERNAME = "Username";
    public static final String JK = "Jenis Kelamin";
    public static final String SEWA = "Sewa";
    public static final String PASSWORD = "PASSWORD";
    public static final String PRESENTASE = "Presentase";

    boolean isAllFieldsChecked = false;
    TextView Persentase;
    EditText Email, Username, Password;
    CheckBox Kamar, Dinner, Transport;
    RadioGroup jenisKelamin;
    RadioButton pria, wanita;
    Button btnSubmit, btnReset;
    SeekBar seekBar;
    Button cancelDialog;
    Button kirimDialog;
    String sSewa;
    String sUsername;
    String sEmail;
    String sPassword;
    String sJk;
    String sSeekBar;
    int status;
    DBHelper db;
//    SQLiteDatabase sqlDatabase;
//    String sqlQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        Email= (EditText) findViewById(R.id.etEmail);
        Username = (EditText) findViewById(R.id.etUsername);
        Password = (EditText) findViewById(R.id.etPassword);

        Kamar = (CheckBox) findViewById(R.id.Kamar);
        Dinner = (CheckBox) findViewById(R.id.Dinner);
        Transport = (CheckBox) findViewById(R.id.Transport);

        jenisKelamin = (RadioGroup) findViewById(R.id.radioJK);

        pria = (RadioButton) findViewById(R.id.jkPria);
        wanita = (RadioButton) findViewById(R.id.jkWanita);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnReset = (Button) findViewById(R.id.btnReset);

        seekBar = (SeekBar) findViewById(R.id.parameterKemudahan);
        Persentase = (TextView) findViewById(R.id.tvNilaiSeekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                String value = String.valueOf(progressValue);
                status =0;
                if(value.equals("0")){
                    Persentase.setText("Kurang");
                    status = 1;
                }else if (value.equals("1")){
                    Persentase.setText("Nyaman");
                    status = 1;
                }else if (value.equals("2")){
                    Persentase.setText("Sangat Nyaman");
                    status = 1;
                }else{
                    status = 0;
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if(isAllFieldsChecked){
                    sUsername = Username.getText().toString();
//                    Toast tNamaWarga = Toast.makeText(getApplicationContext(),"Data Penduduk atas nama " + sNama ,Toast.LENGTH_SHORT);
//                    tNamaWarga.show();
                    sEmail = Email.getText().toString();
                    sPassword = Password.getText().toString();
                    sSeekBar =  Persentase.getText().toString();

                    int selectedJk = jenisKelamin.getCheckedRadioButtonId();
                    if (selectedJk == pria.getId()){
                        sJk = pria.getText().toString();
//                        Toast jkPria = Toast.makeText(getApplicationContext(),"berjenis kelamin " + sPria ,Toast.LENGTH_SHORT);
//                        jkPria.show();
                    }else if (selectedJk == wanita.getId()){
                        sJk = wanita.getText().toString();

                    }

                    String sUsia = Password.getText().toString();


                    sSewa="";
                    if(Kamar.isChecked()){
                        sSewa = sSewa + "Kamar; ";
                    }
                    if(Dinner.isChecked()){
                        sSewa = sSewa + "Breakfast/Dinner; ";
                    }
                    if(Transport.isChecked()){
                        sSewa = sSewa  + "Transport; ";
                    }

                    showDialog();

                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email.setText("");
                Username.setText("");
                Password.setText("");
                Kamar.setChecked(false);
                Dinner.setChecked(false);
                Transport.setChecked(false);
                pria.setChecked(false);
                wanita.setChecked(false);
                seekBar.setProgress(0);
                Persentase.setText("");
            }
        });
    }
    private boolean CheckAllFields(){
        if (Email.length() == 0) {
            Email.setError("Data tidak boleh kosong");
            return false;
        }
        if (Username.length() == 0) {
            Username.setError("Data tidak boleh kosong");
            return false;
        }
        if (Password.length() == 0) {
            Password.setError("Data tidak boleh kosong");
            return false;
        }
        if (jenisKelamin.getCheckedRadioButtonId() == -1) {
//            Toast.makeText(MainActivity.this,"Harap pilih jenis kelamin",Toast.LENGTH_SHORT).show();
            wanita.setError("Harap pilih jenis kelamin");
            return false;
        }
        if (!Kamar.isChecked() && !Dinner.isChecked() && !Transport.isChecked()) {
            Toast.makeText(MainActivity.this,"Harap pilih pekerjaan",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(status == 0){
            Toast.makeText(MainActivity.this,"Harap Berikan Rating",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);

        TextView alertNama = dialog.findViewById(R.id.tNama);
        TextView alertNik = dialog.findViewById(R.id.tNik);
        TextView alertJK = dialog.findViewById(R.id.tJK);
        TextView alertPekerjaan = dialog.findViewById(R.id.tPekerjaan);
        TextView alertPresentase = dialog.findViewById(R.id.tPresentase);

        cancelDialog = (Button) dialog.findViewById(R.id.btn_cancel);
        kirimDialog = (Button) dialog.findViewById(R.id.btn_kirim);



        alertNama.setText(sUsername);
        alertNik.setText(sEmail);
        alertJK.setText(sJk);
        alertPekerjaan.setText(sSewa);
        alertPresentase.setText(sSeekBar);

        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        kirimDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                sqlDatabase= openOrCreateDatabase("db_kanginep", Context.MODE_PRIVATE,null);
//                sqlDatabase.execSQL("CREATE TABLE IF NOT EXISTS tb_data (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  email VARCHAR,username VARCHAR, jenisKelamin VARCHAR, sewa VARCHAR, password VARCHAR, presentase VARCHAR);");
//
//                sqlQuery = "INSERT INTO tb_data (email, username, jenisKelamin, password, sewa, presentase) VALUES ('" + sEmail + "','" + sUsername + "', '" + sJk + "', '" + sPassword +"', '" + sSewa + "', '" + sSeekBar + "');";
//                sqlDatabase.execSQL(sqlQuery);

                Intent intent_kirim = new Intent(MainActivity.this, MainActivity2.class);
                intent_kirim.putExtra(EMAIL, sEmail);
                intent_kirim.putExtra(USERNAME, sUsername);
                intent_kirim.putExtra(JK, sJk);
                intent_kirim.putExtra(SEWA, sSewa);
                intent_kirim.putExtra(PASSWORD, sPassword);
                intent_kirim.putExtra(PRESENTASE, sSeekBar);

                startActivity(intent_kirim);

            }
        });
        dialog.show();
    }
}