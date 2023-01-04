package com.example.mobileproject.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import com.example.mobileproject.R;
import com.example.mobileproject.adapters.DBHelper;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class AlertDialogs {
    DBHelper db;

    public  AlertDialogs()
    {
        this.db = new DBHelper();
    }

    public void brideAlertDialog(Context ctx)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);

        final View view = factory.inflate(R.layout.bride_name_pop, null);

        EditText brideName = view.findViewById(R.id.bride_name);

        AppCompatButton okButton = view.findViewById(R.id.save);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brideName_t = brideName.getText().toString().trim();
                if(!brideName_t.equals(""))
                    db.setBrideName(brideName_t);
                dialog.cancel();

            }
        });


        dialog.show();

    }

    public void groomAlertDialog(Context ctx)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);

        final View view = factory.inflate(R.layout.groom_name_pop, null);

        EditText groomName = view.findViewById(R.id.groom_name);

        AppCompatButton okButton = view.findViewById(R.id.save);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groomName_t = groomName.getText().toString().trim();
                if(!groomName_t.equals(""))
                    db.setGroomName(groomName_t);
                dialog.cancel();

            }
        });


        dialog.show();

    }

    public void messageAlertDialog(Context ctx)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);

        final View view = factory.inflate(R.layout.message_pop, null);

        EditText message = view.findViewById(R.id.message);

        AppCompatButton okButton = view.findViewById(R.id.save);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message_t = message.getText().toString().trim();
                if(!message_t.equals(""))
                    db.setMessage(message_t);
                dialog.cancel();

            }
        });

        dialog.show();

    }

    public void adressAlertDialog(Context ctx)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);

        final View view = factory.inflate(R.layout.adress_pop, null);

        EditText adress = view.findViewById(R.id.address);

        AppCompatButton okButton = view.findViewById(R.id.save);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adress_t = adress.getText().toString().trim();
                if(!adress_t.equals(""))
                    db.setAddress(adress_t);
                dialog.cancel();

            }
        });

        dialog.show();

    }

    public void brideFamilyAlertDialog(Context ctx)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);

        final View view = factory.inflate(R.layout.family_pop, null);

        EditText motherName = view.findViewById(R.id.mother_name);
        EditText fatherName = view.findViewById(R.id.father_name);
        EditText surname = view.findViewById(R.id.surname);

        AppCompatButton okButton = view.findViewById(R.id.save);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String motherName_t = motherName.getText().toString().trim();
                String fatherName_t = fatherName.getText().toString().trim();
                String surname_t = surname.getText().toString();
                if(!motherName_t.equals("") && !fatherName_t.equals("") && !surname_t.equals(""))
                    db.setBrideFamily(motherName_t + " & " + fatherName_t + "\n" + surname_t);
                dialog.cancel();

            }
        });

        dialog.show();

    }

    public void groomFamilyAlertDialog(Context ctx)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);

        final View view = factory.inflate(R.layout.family_pop, null);

        EditText motherName = view.findViewById(R.id.mother_name);
        EditText fatherName = view.findViewById(R.id.father_name);
        EditText surname = view.findViewById(R.id.surname);

        AppCompatButton okButton = view.findViewById(R.id.save);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String motherName_t = motherName.getText().toString().trim();
                String fatherName_t = fatherName.getText().toString().trim();
                String surname_t = surname.getText().toString().trim();
                if(!motherName_t.equals("") && !fatherName_t.equals("") && !surname_t.equals(""))
                    db.setGroomFamily(motherName_t + " & " + fatherName_t + "\n" + surname_t);
                dialog.cancel();

            }
        });

        dialog.show();

    }

    public void date(Context ctx)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);

        final View view = factory.inflate(R.layout.date_pop, null);

        DatePicker date = view.findViewById(R.id.date);

        AppCompatButton okButton = view.findViewById(R.id.save);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String day_t = Integer.toString(date.getDayOfMonth()).trim();
                String month_t = Integer.toString(date.getMonth()).trim();
                String year_t = Integer.toString(date.getYear()).trim();

                if(!day_t.equals("") && !month_t.equals("") && !year_t.equals(""))
                    db.setDate(day_t + ":" + month_t + ":" + year_t);

                dialog.cancel();

            }
        });

        dialog.show();

    }

    public void time(Context ctx) {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);

        final View view = factory.inflate(R.layout.time_pop, null);

        EditText hour = view.findViewById(R.id.hour);
        EditText minute = view.findViewById(R.id.minute);

        AppCompatButton okButton = view.findViewById(R.id.save);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String hour_t = hour.getText().toString().trim();
                String minute_t = minute.getText().toString().trim();

                if(!hour_t.equals("") && !minute_t.equals(""))
                    db.setTime(hour_t + ":" + minute_t);

                dialog.cancel();

            }
        });

        dialog.show();

    }

    public void editProfile(Context ctx)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);

        final View view = factory.inflate(R.layout.edit_profile_pop, null);

        EditText name = view.findViewById(R.id.name);
        EditText surname = view.findViewById(R.id.surname);
        EditText phoneNumber = view.findViewById(R.id.phone_number);

        AppCompatButton okButton = view.findViewById(R.id.save);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_t = name.getText().toString().trim();
                String surname_t = surname.getText().toString().trim();
                String phoneNumber_t = phoneNumber.getText().toString().trim();
                if(!name_t.equals("") && !surname_t.equals("") && !phoneNumber_t.equals(""))
                {
                    SharedPreferencedManager.getInstance(ctx).user_login(SharedPreferencedManager.instance.getUserEmail(), name_t ,surname_t, phoneNumber_t);
                    db.createNewUser(db.getmAuth().getCurrentUser(), ctx);
                }

                dialog.cancel();

            }
        });

        dialog.show();

    }

    public void getGuests(Context ctx)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);


        final View view = factory.inflate(R.layout.add_guest_pop, null);

        Spinner gender = view.findViewById(R.id.gender);
        List<String> list = new ArrayList<>();
        list.add("male");
        list.add("female");
        ArrayAdapter<String> adapter  = new ArrayAdapter<>(ctx, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
        gender.setAdapter(adapter);

        EditText name = view.findViewById(R.id.name);
        EditText surname = view.findViewById(R.id.surname);
        EditText email = view.findViewById(R.id.email);
        EditText phone = view.findViewById(R.id.tel_num);

        AppCompatButton addButton = view.findViewById(R.id.add_guest);
        AppCompatButton cancelButton = view.findViewById(R.id.cancel);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_t = name.getText().toString().trim();
                String surname_t = surname.getText().toString().trim();
                String gender_t = (String) gender.getSelectedItem();
                String email_t = email.getText().toString().trim();
                String phone_t = phone.getText().toString().trim();

                if(!name_t.equals("") && !surname_t.equals("") && !gender_t.equals("") && !email_t.equals("") && !phone_t.equals(""))
                {
                    db.addGuest(name_t, surname_t, gender_t, email_t, phone_t)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    dialog.cancel();
                                }
                            });
                }

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

    public void getInvitations(Context ctx)
    {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(ctx, R.style.CustomAlertDialog);
        LayoutInflater factory = LayoutInflater.from(ctx);

        final View view = factory.inflate(R.layout.add_invitation_pop, null);

        EditText verification = view.findViewById(R.id.verification_code);

        AppCompatButton addButton = view.findViewById(R.id.add_invitation);
        AppCompatButton cancelButton = view.findViewById(R.id.cancel);

        dialogBox.setView(view);
        final AlertDialog dialog = dialogBox.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verification_t = verification.getText().toString().trim();
                if(!verification_t.equals(""))
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
