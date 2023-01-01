package com.example.mobileproject.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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
}
