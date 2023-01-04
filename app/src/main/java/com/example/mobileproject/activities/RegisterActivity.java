package com.example.mobileproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.R;
import com.example.mobileproject.activities.LoginActivity;
import com.example.mobileproject.activities.MainActivity;
import com.example.mobileproject.adapters.DBHelper;
import com.example.mobileproject.utilities.SharedPreferencedManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class RegisterActivity extends AppCompatActivity {

    EditText emailE, passwordE;
    AppCompatButton register;
    TextView login;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailE = findViewById(R.id.email);
        passwordE = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login_text);

        db = new DBHelper();

        if(SharedPreferencedManager.getInstance(this).isLoggedIn())
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailE.getText().toString().trim();
                String password = passwordE.getText().toString().trim();

                if(!email.equals("") && !password.equals(""))
                {
                    db.registerEmailandPassword(email, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    SharedPreferencedManager.getInstance(getApplicationContext()).user_login(email, "Name", "Surname", "(000) 000 00 00");
                                    db.createNewUser(authResult.getUser(), getApplicationContext());
                                    db.setBrideName("Bride");
                                    db.setGroomName("Groom");
                                    db.setMessage("Lorem ipsum dolor sit amet " +
                                            "Lorem ipsum dolor sit amet " +
                                            "Lorem ipsum dolor sit amet " +
                                            "Lorem ipsum dolor sit amet " +
                                            "Lorem ipsum dolor sit amet");
                                    db.setAddress("Adress");
                                    db.setBrideFamily("Family");
                                    db.setGroomFamily("Family");
                                    db.setTime("20:00");
                                    db.setDate("01:01:2023");
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else
                    Toast.makeText(RegisterActivity.this, "Email and password can nat be empty!", Toast.LENGTH_SHORT).show();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }







}