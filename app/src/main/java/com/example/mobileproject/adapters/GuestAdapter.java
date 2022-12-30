package com.example.mobileproject.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.mobileproject.R;
import com.example.mobileproject.models.Guest;
import com.example.mobileproject.models.Suit;

import java.util.List;

public class GuestAdapter extends ArrayAdapter<Guest> {

    private final LayoutInflater inflater;

    public GuestAdapter(Activity context, List<Guest> views) {
        super(context, 0, views);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Guest guestData = getItem(position);
        View currencyView = inflater.inflate(R.layout.list_item_add_guests ,null, true);

        TextView name = (TextView) currencyView.findViewById(R.id.name);
        TextView surname = (TextView) currencyView.findViewById(R.id.surname);
        TextView gender = (TextView) currencyView.findViewById(R.id.gender);
        TextView email = (TextView) currencyView.findViewById(R.id.email);
        TextView phoneNum = (TextView) currencyView.findViewById(R.id.phone_number);
        ImageView avatar = (ImageView) currencyView.findViewById(R.id.avatar);
        CheckBox isConfirmed = (CheckBox) currencyView.findViewById(R.id.isConfirmed);

        name.setText("Name: " + guestData.getName());
        surname.setText("Surname: " + guestData.getSurname());
        gender.setText("Gender: " + guestData.getGender());
        email.setText("E-mail: " + guestData.getEmail());
        phoneNum.setText("Phone Num: " + guestData.getPhoneNum());

        if(guestData.getGender().equals("female"))
            avatar.setImageResource(R.drawable.woman);
        else if(guestData.getGender().equals("male"))
            avatar.setImageResource(R.drawable.man);

        isConfirmed.setActivated(guestData.isConfirmed());


        return currencyView;
    }

}