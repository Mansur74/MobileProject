package com.example.mobileproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.adapters.DBHelper;

public class InvitationActivity extends AppCompatActivity {

    AppCompatButton confirm, reject;
    Toolbar toolbar;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);

        confirm = findViewById(R.id.confirm);
        reject = findViewById(R.id.reject);
        toolbar = findViewById(R.id.toolbar);
        db = new DBHelper();

        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("The Invitation");

        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");
        String verification = intent.getStringExtra("verification");


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.setConfirm(userId, verification);
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.setReject(userId, verification);
            }
        });



    }
}