package com.example.mobileproject.fragments;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.R;
import com.example.mobileproject.activities.MainActivity;
import com.example.mobileproject.adapters.DBHelper;

public class MyWeddingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    DBHelper db;
    TextView brideName_t, groomName_t, message_t, address_t, brideFamily_t, groomFamily_t, time_t, date_t;
    AppCompatButton send;

    public MyWeddingFragment() {
        // Required empty public constructor
    }


    public static MyWeddingFragment newInstance(String param1, String param2) {
        MyWeddingFragment fragment = new MyWeddingFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_wedding, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("My Wedding");

        brideName_t = view.findViewById(R.id.bride_name);
        groomName_t = view.findViewById(R.id.groom_name);
        brideFamily_t = view.findViewById(R.id.bride_family);
        groomFamily_t = view.findViewById(R.id.groom_family);
        message_t = view.findViewById(R.id.message);
        address_t = view.findViewById(R.id.address);
        time_t = view.findViewById(R.id.time);
        date_t = view.findViewById(R.id.date);

        send = view.findViewById(R.id.send);

        db = new DBHelper();
        db.getTheInvitation(db.getmAuth().getUid(), brideName_t, groomName_t, message_t, address_t, brideFamily_t, groomFamily_t, time_t, date_t);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.sendSms(getActivity(), "Dugunumuze davetlisiniz, asagida size gonderilen verification kodunuz ile davetiyemizi goruntuleyip davetimize onay verebilirsiniz.");
            }
        });

        return view;
    }


}