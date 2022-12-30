package com.example.mobileproject.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.models.Invitation;

import java.util.List;

public class InvitationAdapter extends ArrayAdapter<Invitation> {

    private final LayoutInflater inflater;

    public InvitationAdapter(Activity context, List<Invitation> views) {
        super(context, 0, views);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Invitation invitationData = getItem(position);
        View currencyView = inflater.inflate(R.layout.list_item_invitation, null, true);

        TextView verification = (TextView) currencyView.findViewById(R.id.verification_code);
        TextView userId = (TextView) currencyView.findViewById(R.id.user_id);

        verification.setText("Verification Code: " + invitationData.getVerification());
        userId.setText("User Id: " + invitationData.getUser_id());


        return currencyView;
    }
}