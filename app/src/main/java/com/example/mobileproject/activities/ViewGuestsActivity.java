package com.example.mobileproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.adapters.DBHelper;

public class ViewGuestsActivity extends AppCompatActivity {

    AppCompatButton invitation;
    ImageView back;
    Toolbar toolbar;
    ListView guestList;
    DBHelper db;
    String userId, verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guests);

        toolbar = findViewById(R.id.toolbar);
        db = new DBHelper();
        guestList = findViewById(R.id.guest_list);
        back = findViewById(R.id.back);
        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("The Guest List");

        invitation = findViewById(R.id.invitation_but);
        userId = getIntent().getStringExtra("user_id");
        verification = getIntent().getStringExtra("verification");

        db.getGuests(ViewGuestsActivity.this, userId, guestList);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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