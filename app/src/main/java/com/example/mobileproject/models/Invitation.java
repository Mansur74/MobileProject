package com.example.mobileproject.models;

public class Invitation {

    String verification, user_id;

    public Invitation(String verification, String user_id)
    {
        this.verification = verification;
        this.user_id = user_id;
    }

    public String getVerification() {
        return verification;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
