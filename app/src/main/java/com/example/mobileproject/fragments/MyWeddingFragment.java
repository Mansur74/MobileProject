package com.example.mobileproject.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.R;
import com.example.mobileproject.adapters.DBHelper;
import com.example.mobileproject.utilities.AlertDialogs;

public class MyWeddingFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private final int REQUEST_READ_PHONE_STATE=1;

    DBHelper db;
    AlertDialogs alertDialogs;
    TextView brideName_t, groomName_t, message_t, address_t, brideFamily_t, groomFamily_t, time_t, date_t;
    AppCompatButton send;

    private ActivityResultLauncher<String> mPermissionResult = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if(result)
                    {
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage("05350517263", null, "Verification Code: ", null, null);
                        Toast.makeText(getContext(),"Message is sended to", Toast.LENGTH_SHORT).show();

                    }
                    else
                        return;
                }
            }
    );


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

        alertDialogs = new AlertDialogs();

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
                mPermissionResult.launch(Manifest.permission.SEND_SMS);
            }
        });

        brideName_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogs.brideAlertDialog(getContext());
            }
        });

        groomName_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogs.groomAlertDialog(getContext());
            }
        });

        message_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogs.messageAlertDialog(getContext());
            }
        });

        return view;


    }








}