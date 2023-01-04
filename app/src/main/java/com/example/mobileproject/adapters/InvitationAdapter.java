package com.example.mobileproject.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.models.Invitation;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class InvitationAdapter extends ArrayAdapter<Invitation> {

    private final LayoutInflater inflater;
    private DBHelper db;
    private Activity ctx;
    private InvitationAdapter adapter;

    public InvitationAdapter(Activity context, List<Invitation> views) {
        super(context, 0, views);
        inflater = LayoutInflater.from(context);
        db = new DBHelper();
        ctx = context;
        adapter = this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Invitation invitationData = getItem(position);
        View currencyView = inflater.inflate(R.layout.list_item_invitation, null, true);

        TextView verification = (TextView) currencyView.findViewById(R.id.verification_code);
        ImageButton cancel = (ImageButton) currencyView.findViewById(R.id.delete_invitation);
        RelativeLayout layout = (RelativeLayout) currencyView.findViewById(R.id.invitation_layout);

        verification.setText("My guess ID: " + invitationData.getVerification());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.isGuessExist(invitationData.getUser_id(), invitationData.getVerification(), ctx);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteInvitation(invitationData.getVerification())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });


        return currencyView;
    }
}