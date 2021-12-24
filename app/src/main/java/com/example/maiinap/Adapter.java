package com.example.maiinap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter <Adapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList contact_id, contact_username, contact_password, contact_email;


    Adapter(Activity activity, Context context, ArrayList contact_id, ArrayList contact_username, ArrayList contact_password,
                   ArrayList contact_email){
        this.activity = activity;
        this.context = context;
        this.contact_id = contact_id;
        this.contact_username = contact_username;
        this.contact_password = contact_password;
        this.contact_email = contact_email;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_list, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.contact_email_txt.setText(String.valueOf(contact_username.get(position)));
        holder.contact_username_txt.setText(String.valueOf(contact_password.get(position)));

        //Recyclerview onClickListener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditContact.class);
                intent.putExtra("id", String.valueOf(contact_id.get(position)));
                intent.putExtra("username", String.valueOf(contact_username.get(position)));
                intent.putExtra("password", String.valueOf(contact_password.get(position)));
                intent.putExtra("email", String.valueOf(contact_email.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contact_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contact_email_txt, contact_username_txt;
        CardView cardView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_email_txt = itemView.findViewById(R.id.text1);
            contact_username_txt = itemView.findViewById(R.id.text2);
            cardView = itemView.findViewById(R.id.row);
        }
    }
}
