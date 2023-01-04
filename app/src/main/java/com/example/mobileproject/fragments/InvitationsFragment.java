package com.example.mobileproject.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.activities.InvitationActivity;
import com.example.mobileproject.adapters.DBHelper;
import com.example.mobileproject.adapters.GuestAdapter;
import com.example.mobileproject.adapters.InvitationAdapter;
import com.example.mobileproject.models.Invitation;
import com.example.mobileproject.utilities.AlertDialogs;
import com.google.android.gms.tasks.OnSuccessListener;

public class InvitationsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    DBHelper db;
    ListView invitationList;
    AlertDialogs alertDialogs;

    public InvitationsFragment() {
        // Required empty public constructor
    }


    public static InvitationsFragment newInstance(String param1, String param2) {
        InvitationsFragment fragment = new InvitationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invitations, container, false);
        CardView addInvitationButton = view.findViewById(R.id.add_invitation);
        db = new DBHelper();
        invitationList = view.findViewById(R.id.invitations_list);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("My Invitations");

        alertDialogs = new AlertDialogs();

        addInvitationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogs.getInvitations(getContext());
            }
        });

        db.getInvitations(getActivity(), invitationList);

        return view;
    }


}