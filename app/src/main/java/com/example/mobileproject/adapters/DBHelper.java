package com.example.mobileproject.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.example.mobileproject.activities.InvitationActivity;
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

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public Task<AuthResult> registerEmailandPassword(String email, String password)
    {
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    public void createNewUser(FirebaseUser firebaseUser, Context ctx)
    {
        DatabaseReference ref = db.child("users").child(firebaseUser.getUid());
        ref.child("user_id").setValue(firebaseUser.getUid());
        ref.child("email").setValue(SharedPreferencedManager.getInstance(ctx).getUserEmail());
        ref.child("name").setValue(SharedPreferencedManager.getInstance(ctx).getName());
        ref.child("surname").setValue(SharedPreferencedManager.getInstance(ctx).getSurname());
        ref.child("phone_num").setValue(SharedPreferencedManager.getInstance(ctx).getPhoneNumber());
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

    public void getGuests(Activity ctx, String userId, ListView guestList)
    {
        List<Guest> guests = new ArrayList<>();
        DatabaseReference ref = db.child("users").child(userId).child("guests");
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
                        String surname = (String)guestData.get("surname");
                        String gender = (String)guestData.get("gender");
                        String email = (String)guestData.get("email");
                        String phoneNum = (String)guestData.get("phone_num");
                        boolean isConfirmed = (boolean)guestData.get("isConfirmed");

                        guests.add(new Guest(name, surname, gender, email, phoneNum, key, isConfirmed));
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

    public Task<Void> deleteGuest(String verification)
    {
         DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("guests").child(verification);
         db.child("verifications").child(verification).getRef().removeValue();

         return ref.getRef().removeValue();

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

    public Task<Void> deleteInvitation(String verification)
    {
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("invitations").child(verification);

        return ref.removeValue();

    }

    public void setConfirm(String user_id, String verification)
    {
        DatabaseReference ref = db.child("users").child(user_id).child("guests").child(verification);
        ref.child("isConfirmed").setValue(true);
    }

    public void setReject(String user_id, String verification)
    {
        DatabaseReference ref = db.child("users").child(user_id).child("guests").child(verification);
        ref.child("isConfirmed").setValue(false);
    }

    public void setBrideName(String brideName)
    {
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("my_wedding").child("bride_name");
        ref.setValue(brideName);
    }

    public void setGroomName(String groomName)
    {
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("my_wedding").child("groom_name");
        ref.setValue(groomName);
    }

    public void setMessage(String message)
    {
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("my_wedding").child("message");
        ref.setValue(message);
    }
    public void setAddress(String adress)
    {
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("my_wedding").child("address");
        ref.setValue(adress);
    }


    public void setBrideFamily(String brideFamily)
    {
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("my_wedding").child("bride_family");
        ref.setValue(brideFamily);
    }

    public void setGroomFamily(String groomFamily)
    {
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("my_wedding").child("groom_family");
        ref.setValue(groomFamily);
    }

    public void setTime(String time)
    {
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("my_wedding").child("time");
        ref.setValue(time);
    }

    public void setDate(String date)
    {
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("my_wedding").child("date");
        ref.setValue(date);
    }

    public void getTheInvitation(String user_id, TextView brideName_t, TextView groomName_t, TextView message_t, TextView address_t, TextView brideFamily_t, TextView groomFamily_t, TextView time_t, TextView date_t)
    {
        DatabaseReference ref = db.child("users").child(user_id).child("my_wedding");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> dataMap = (HashMap<String, Object>) snapshot.getValue();
                String brideName = (String) dataMap.get("bride_name");
                String groomName = (String) dataMap.get("groom_name");
                String message = (String) dataMap.get("message");
                String address = (String) dataMap.get("address");
                String brideFamily = (String) dataMap.get("bride_family");
                String groomFamily = (String) dataMap.get("groom_family");
                String time = (String) dataMap.get("time");
                String date = (String) dataMap.get("date");

                brideName_t.setText(brideName);
                groomName_t.setText(groomName);
                message_t.setText(message);
                address_t.setText(address);
                brideFamily_t.setText(brideFamily);
                groomFamily_t.setText(groomFamily);
                time_t.setText(time);
                date_t.setText(date);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void sendSms(Activity ctx, String message)
    {
        List<Guest> guests = new ArrayList<>();
        DatabaseReference ref = db.child("users").child(mAuth.getUid()).child("guests");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
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
                        String surname = (String)guestData.get("surname");
                        String phoneNum = (String)guestData.get("phone_num");

                        SmsManager sms = SmsManager.getDefault();
                        ActivityCompat.requestPermissions(ctx, new String[] { Manifest.permission.SEND_SMS}, 1);
                        sms.sendTextMessage(phoneNum, null, message + "\nVerification Code: " + key, null, null);
                        Toast.makeText(ctx,"Message is sended to " + name + " " + surname , Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void isGuessExist(String userId, String verification, Context ctx)
    {
        DatabaseReference ref = db.child("users").child(userId).child("guests").child(verification);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    Intent intent = new Intent(ctx, InvitationActivity.class);
                    intent.putExtra("user_id", userId);
                    intent.putExtra("verification", verification);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
