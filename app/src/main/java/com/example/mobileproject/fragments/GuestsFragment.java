package com.example.mobileproject.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.mobileproject.adapters.GuestAdapter;
import com.example.mobileproject.models.Guest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuestsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    DBHelper db;
    ListView guestList;

    public GuestsFragment() {
        // Required empty public constructor
    }

    public static GuestsFragment newInstance(String param1, String param2) {
        GuestsFragment fragment = new GuestsFragment();
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
        View view = inflater.inflate(R.layout.fragment_guests, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("Guests");

        CardView addGuestButton = view.findViewById(R.id.add_guest);
        db = new DBHelper();
        guestList = view.findViewById(R.id.guest_list);

        addGuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(view);
            }
        });

        db.getGuests(getActivity(), guestList);

        return view;


    }

    public void alertDialog(View v)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(getContext());


        final View view = factory.inflate(R.layout.add_guest_pop, null);

        EditText name = view.findViewById(R.id.name);
        EditText surname = view.findViewById(R.id.surname);
        EditText gender = view.findViewById(R.id.gender);
        EditText email = view.findViewById(R.id.email);
        EditText phone = view.findViewById(R.id.tel_num);

        AppCompatButton addButton = view.findViewById(R.id.add_guest);
        AppCompatButton cancelButton = view.findViewById(R.id.cancel);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_t = name.getText().toString();
                String surname_t = surname.getText().toString();
                String gender_t = gender.getText().toString();
                String email_t = email.getText().toString();
                String phone_t = phone.getText().toString();

                db.addGuest(name_t, surname_t, gender_t, email_t, phone_t)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                dialog.cancel();
                            }
                        });

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