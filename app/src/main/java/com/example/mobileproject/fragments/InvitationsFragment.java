package com.example.mobileproject.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.adapters.DBHelper;

public class InvitationsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    DBHelper db;
    ListView invitationList;

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
        textView.setText("Invitations");

        addInvitationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(view);
            }
        });

        db.getInvitations(getActivity(), invitationList);
        return view;
    }


    public void alertDialog(View v)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(getContext());
        LayoutInflater factory = LayoutInflater.from(getContext());

        final View view = factory.inflate(R.layout.add_invitation_pop, null);

        EditText verification = view.findViewById(R.id.verification_code);

        AppCompatButton addButton = view.findViewById(R.id.add_invitation);
        AppCompatButton cancelButton = view.findViewById(R.id.cancel);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verification_t = verification.getText().toString();
                db.addInvitation(verification_t, dialog);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();

    }
}