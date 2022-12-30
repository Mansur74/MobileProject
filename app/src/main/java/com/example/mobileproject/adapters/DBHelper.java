package com.example.mobileproject.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

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

    public List<Guest> getGuests(Context ctx)
    {
        List<Guest> guests = new ArrayList<>();
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("guests");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> dataMap = (HashMap<String, Object>) snapshot.getValue();
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return guests;
    }

}
