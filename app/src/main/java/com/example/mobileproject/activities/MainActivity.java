package com.example.mobileproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

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
    private static Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new HomeFragment();
        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.nav_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();


        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        MainActivity.fragment = new HomeFragment();
                        break;

                    case R.id.nav_guests:
                        MainActivity.fragment = new GuestsFragment();
                        break;

                    case R.id.nav_profile:
                        MainActivity.fragment = new ProfileFragment();
                        break;

                    case R.id.nav_my_wedding:
                        MainActivity.fragment = new MyWeddingFragment();
                        break;

                    case R.id.nav_invitations:
                        MainActivity.fragment = new InvitationsFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, MainActivity.fragment).commit();

                return true;
            }
        });

    }

    // gesture detector
    final GestureDetector gesture = new GestureDetector(getApplication(),
            new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                       float velocityY) {
                    final int SWIPE_MIN_DISTANCE = 120;
                    final int SWIPE_MAX_OFF_PATH = 250;
                    final int SWIPE_THRESHOLD_VELOCITY = 200;
                    try {
                        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                            return false;
                        if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                            leftSwipe(MainActivity.fragment);
                        } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                            rightSwipe(MainActivity.fragment);
                        }

                    } catch (Exception e) {
                        // nothing
                    }
                    return super.onFling(e1, e2, velocityX, velocityY);
                }
            });

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return gesture.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        gesture.onTouchEvent(event);
        super.dispatchTouchEvent(event);
        return true;
    }


    // gesture actions
    public void leftSwipe(Fragment current)
    {

        if(current instanceof HomeFragment)
        {
            MainActivity.fragment = new GuestsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, MainActivity.fragment).commit();
            navigationView.setSelectedItemId(R.id.nav_guests);
        }
        else if(current instanceof GuestsFragment)
        {
            MainActivity.fragment = new MyWeddingFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, MainActivity.fragment).commit();
            navigationView.setSelectedItemId(R.id.nav_my_wedding);
        }
        else if(current instanceof MyWeddingFragment)
        {
            MainActivity.fragment = new InvitationsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, MainActivity.fragment).commit();
            navigationView.setSelectedItemId(R.id.nav_invitations);
        }
        else if(current instanceof InvitationsFragment)
        {
            MainActivity.fragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, MainActivity.fragment).commit();
            navigationView.setSelectedItemId(R.id.nav_profile);
        }



    }

    public void rightSwipe(Fragment current)
    {
        if(current instanceof GuestsFragment)
        {
            MainActivity.fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, MainActivity.fragment).commit();
            navigationView.setSelectedItemId(R.id.nav_home);
        }
        else if(current instanceof MyWeddingFragment)
        {
            MainActivity.fragment = new GuestsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, MainActivity.fragment).commit();
            navigationView.setSelectedItemId(R.id.nav_guests);
        }
        else if(current instanceof InvitationsFragment)
        {
            MainActivity.fragment = new MyWeddingFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, MainActivity.fragment).commit();
            navigationView.setSelectedItemId(R.id.nav_my_wedding);
        }

        else if(current instanceof ProfileFragment)
        {
            MainActivity.fragment = new InvitationsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, MainActivity.fragment).commit();
            navigationView.setSelectedItemId(R.id.nav_invitations);
        }
    }
}