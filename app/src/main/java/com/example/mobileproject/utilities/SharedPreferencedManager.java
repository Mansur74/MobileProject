package com.example.mobileproject.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencedManager {

    public static SharedPreferencedManager instance;
    public static Context mCtx;

    private static final String SHARED_PREF_NAME = "Shared1";
    private static final String USER_EMAIL_KEY = "email";
    private static final String USER_NAME_KEY = "name";
    private static final String USER_SURNAME_KEY = "surname";
    private static final String USER_PHONE_NUMBER_KEY = "phone_num";

    public SharedPreferencedManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPreferencedManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencedManager(context);
        }
        return instance;
    }

    public String getUserEmail()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_EMAIL_KEY, null);
    }

    public String getName()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME_KEY, null);
    }

    public String getSurname()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_SURNAME_KEY, null);
    }

    public String getPhoneNumber()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_PHONE_NUMBER_KEY, null);
    }

    public void user_login(String email, String name, String surname, String phoneNum)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER_EMAIL_KEY, email);
        editor.putString(USER_NAME_KEY, name);
        editor.putString(USER_SURNAME_KEY, surname);
        editor.putString(USER_PHONE_NUMBER_KEY, phoneNum);

        editor.apply();

    }

    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(USER_EMAIL_KEY, null) != null)
            return true;
        return false;
    }

    public void userLogout()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
