package com.example.mobileproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mobileproject.R;
import com.example.mobileproject.fragments.GuestsFragment;
import com.example.mobileproject.fragments.HomeFragment;
import com.example.mobileproject.fragments.InvitationsFragment;
import com.example.mobileproject.fragments.MyWeddingFragment;
import com.example.mobileproject.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.nav_guests:
                        fragment = new GuestsFragment();
                        break;

                    case R.id.nav_profile:
                        fragment = new ProfileFragment();
                        break;

                    case R.id.nav_my_wedding:
                        fragment = new MyWeddingFragment();
                        break;

                    case R.id.nav_invitations:
                        fragment = new InvitationsFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();

                return true;
            }
        });

    }
}