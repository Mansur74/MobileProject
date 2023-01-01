package com.example.mobileproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.adapters.DBHelper;

public class InvitationActivity extends AppCompatActivity {

    AppCompatButton confirm, reject;
    Toolbar toolbar;
    ListView guestList;
    DBHelper db;
    TextView brideName_t, groomName_t, message_t, address_t, brideFamily_t, groomFamily_t, time_t, date_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);

        confirm = findViewById(R.id.confirm);
        reject = findViewById(R.id.reject);
        toolbar = findViewById(R.id.toolbar);
        db = new DBHelper();

        guestList = findViewById(R.id.guest_list);

        brideName_t = findViewById(R.id.bride_name);
        groomName_t = findViewById(R.id.groom_name);
        brideFamily_t = findViewById(R.id.bride_family);
        groomFamily_t = findViewById(R.id.groom_family);
        message_t = findViewById(R.id.message);
        address_t = findViewById(R.id.address);
        time_t = findViewById(R.id.time);
        date_t = findViewById(R.id.date);

        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("The Invitation");

        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");
        String verification = intent.getStringExtra("verification");

        db.getTheInvitation(userId, brideName_t, groomName_t, message_t, address_t, brideFamily_t, groomFamily_t, time_t, date_t);

        //db.getGuests(InvitationActivity.this, guestList);


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