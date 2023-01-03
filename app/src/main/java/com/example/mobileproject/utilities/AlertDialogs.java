package com.example.mobileproject.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import com.example.mobileproject.R;
import com.example.mobileproject.adapters.DBHelper;

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
                String brideName_t = brideName.getText().toString();
                if(!brideName_t.trim().equals(""))
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
                String groomName_t = groomName.getText().toString();
                if(!groomName_t.trim().equals(""))
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
                String message_t = message.getText().toString();
                if(!message_t.trim().equals(""))
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
                String adress_t = adress.getText().toString();
                if(!adress_t.trim().equals(""))
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
                String motherName_t = motherName.getText().toString();
                String fatherName_t = fatherName.getText().toString();
                String surname_t = surname.getText().toString();
                if(!motherName_t.trim().equals("") && !fatherName_t.trim().equals("") && !surname_t.trim().equals(""))
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
                String motherName_t = motherName.getText().toString();
                String fatherName_t = fatherName.getText().toString();
                String surname_t = surname.getText().toString();
                if(!motherName_t.trim().equals("") && !fatherName_t.trim().equals("") && !surname_t.trim().equals(""))
                    db.setGroomFamily(motherName_t + " & " + fatherName_t + "\n" + surname_t);
                dialog.cancel();

            }
        });

        dialog.show();

    }

    public void timeAndDate(Context ctx)
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

                String day_t = Integer.toString(date.getDayOfMonth());
                String month_t = Integer.toString(date.getMonth());
                String year_t = Integer.toString(date.getYear());

                if(!day_t.trim().equals("") && !month_t.trim().equals("") && !year_t.trim().equals(""))
                    db.setDate(day_t + ":" + month_t + ":" + year_t);

                dialog.cancel();

            }
        });

        dialog.show();

    }
}
