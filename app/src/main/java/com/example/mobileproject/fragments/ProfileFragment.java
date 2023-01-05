package com.example.mobileproject.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.activities.RegisterActivity;
import com.example.mobileproject.utilities.AlertDialogs;
import com.example.mobileproject.utilities.SharedPreferencedManager;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    AppCompatButton logOut;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    AlertDialogs alertDialogs;
    TextView email, nameSurname, phoneNum;
    ImageButton edit;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("Profile");

        alertDialogs = new AlertDialogs();

        email = view.findViewById(R.id.email);
        nameSurname = view.findViewById(R.id.name_surname);
        phoneNum = view.findViewById(R.id.phone_number);
        edit = view.findViewById(R.id.edit_profile);

        email.setText(SharedPreferencedManager.getInstance(getContext()).getUserEmail());
        nameSurname.setText(SharedPreferencedManager.getInstance(getContext()).getName() + " " + SharedPreferencedManager.getInstance(getContext()).getSurname());
        phoneNum.setText(SharedPreferencedManager.getInstance(getContext()).getPhoneNumber());

        logOut = view.findViewById(R.id.logout);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencedManager.getInstance(getContext()).userLogout();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogs.editProfile(getContext(), nameSurname, phoneNum);
            }
        });
        return view;
    }
}