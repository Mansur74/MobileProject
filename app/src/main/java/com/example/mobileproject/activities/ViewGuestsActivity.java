package com.example.mobileproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.mobileproject.R;
import com.example.mobileproject.adapters.DBHelper;

public class ViewGuestsActivity extends AppCompatActivity {

    AppCompatButton invitation;
    ListView guestList;
    DBHelper db;
    String userId, verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guests);

        db = new DBHelper();
        guestList = findViewById(R.id.guest_list);
        invitation = findViewById(R.id.invitation_but);
        userId = getIntent().getStringExtra("user_id");
        verification = getIntent().getStringExtra("verification");

        db.getGuests(ViewGuestsActivity.this, userId, guestList);

        invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewGuestsActivity.this, InvitationActivity.class);
                intent.putExtra("user_id", userId);
                intent.putExtra("verification", verification);
                startActivity(intent);
                finish();
            }
        });
    }
}