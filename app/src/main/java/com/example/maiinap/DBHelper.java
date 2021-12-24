package com.example.maiinap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper{

        private Context context;

        public static final String database_name = "db_kanginep";
        public static final String table_name = "tb_inep";
        public static final String row_id = "id";
        public static final String row_email = "email";
        public static final String row_username = "username";
        public static final String row_jenis_kelamin = "gender";
        public static final String row_password = "password";
        public static final String row_sewa = "sewa";
        public static final String row_persentase = "persentase";

        public static final String table_contact = "tb_kontak";
        public static final String row_user = "username";
        public static final String row_emil = "email";
        public static final String row_swa = "sewa";
        public static final String row_pass = "password";

        private static SQLiteDatabase db;

        DBHelper(@Nullable Context context) {
            super(context, database_name, null, 2);
            db = getWritableDatabase();
            this.context = context;
        }


    @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + row_email + " TEXT," + row_username + " TEXT," + row_jenis_kelamin + " TEXT," + row_password + " TEXT," + row_sewa + " TEXT," + row_persentase + " TEXT)";

            String query_contact = "CREATE TABLE " + table_contact + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + row_user + " TEXT," + row_emil + " TEXT," + row_swa + " TEXT," + row_pass + " TEXT)";
            db.execSQL(query);
            db.execSQL(query_contact);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + table_name);
            db.execSQL("DROP TABLE IF EXISTS " + table_contact);
        }

        //Insert Data
        public static void insertUser(ContentValues values){
            db.insert(table_name, null, values);
        }

        public static void insertContact(ContentValues values){
            db.insert(table_contact, null, values);
        }

        public Cursor readAllData(){
            String sql = "select * from " + table_contact;
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = null;
            if (db != null) {
                cursor = db.rawQuery(sql, null);
            }
            return cursor;
        }

        void updateData(String row_id, String user, String emil, String swa, String pass){
            ContentValues cv = new ContentValues();
            cv.put(row_user, user);
            cv.put(row_emil, emil);
            cv.put(row_swa, swa);
            cv.put(row_pass, pass);

            long result = db.update(table_contact, cv, "id=?", new String[]{row_id});
            if(result == -1){
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        }

        void deleteOneRow(String row_id){
            long result = db.delete(table_contact, "id=?", new String[]{row_id});
            if(result == -1){
                Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            }
        }
    }

