package com.example.mobileproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.mobileproject.models.Invitation;
import com.example.mobileproject.utilities.SharedPreferencedManager;
import com.example.mobileproject.models.Guest;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper {
    private FirebaseAuth mAuth;
    private DatabaseReference db;

    public DBHelper()
    {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
    }

    public Task<AuthResult> registerEmailandPassword(String email, String password)
    {
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    public void createNewUser(FirebaseUser firebaseUser, Context ctx)
    {
        DatabaseReference ref = db.child("users").child(firebaseUser.getUid());
        ref.child("user_id").setValue(firebaseUser.getUid());
        ref.child("email").setValue(SharedPreferencedManager.getInstance(ctx).getUserEmailKey());
    }

    public Task<Void> addGuest(String name, String surname, String gender, String email, String phoneNum)
    {
        int max = 999999;
        int min = 100000;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("guests").child(Integer.toString(rand));

        HashMap<String, Object> datas = new HashMap<>();
        datas.put("name", name);
        datas.put("surname", surname);
        datas.put("gender", gender);
        datas.put("email", email);
        datas.put("phone_num", phoneNum);
        datas.put("isConfirmed", false);

        db.child("verifications").child(Integer.toString(rand)).setValue(mAuth.getUid());
        return ref.setValue(datas);

    }

    public void getGuests(Activity ctx, ListView guestList)
    {
        List<Guest> guests = new ArrayList<>();
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("guests");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                guests.clear();
                HashMap<String, Object> dataMap = (HashMap<String, Object>) snapshot.getValue();
                if(dataMap != null)
                {
                    for (String key : dataMap.keySet())
                    {
                        Object data = dataMap.get(key);

                        HashMap<String, Object> guestData = (HashMap<String, Object>) data;
                        String name = (String)guestData.get("name");
                        String surname = (String)guestData.get("name");
                        String gender = (String)guestData.get("gender");
                        String email = (String)guestData.get("name");
                        String phoneNum = (String)guestData.get("name");
                        boolean isConfirmed = (boolean)guestData.get("isConfirmed");

                        guests.add(new Guest(name, surname, gender, email, phoneNum, isConfirmed));
                    }

                    if (ctx!=null){
                        GuestAdapter adapter = new GuestAdapter(ctx, guests);
                        guestList.setAdapter(adapter);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void addInvitation(String verification, AlertDialog dialog)
    {

        DatabaseReference ref = db.child("verifications").child(verification);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String user_id = snapshot.getValue(String.class);
                if(user_id != null)
                {
                    DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("invitations").child(verification);
                    ref.child("user_id").setValue(user_id);
                    ref.child("verification").setValue(verification);
                }

                dialog.cancel();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getInvitations(Activity ctx, ListView invitationList)
    {
        List<Invitation> invitations = new ArrayList<>();
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("invitations");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                invitations.clear();
                HashMap<String, Object> dataMap = (HashMap<String, Object>) snapshot.getValue();
                if(dataMap != null)
                {
                    for (String key : dataMap.keySet())
                    {
                        Object data = dataMap.get(key);

                        HashMap<String, Object> guestData = (HashMap<String, Object>) data;
                        String user_id = (String)guestData.get("user_id");
                        String verification = (String)guestData.get("verification");

                        invitations.add(new Invitation(verification, user_id));
                    }

                    if (ctx!=null){
                        InvitationAdapter adapter = new InvitationAdapter(ctx, invitations);
                        invitationList.setAdapter(adapter);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}
