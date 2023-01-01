package com.example.mobileproject.models;

public class Guest {

    String name, surname, gender, email, phoneNum, verification;
    boolean isConfirmed;

    public Guest(String name, String surname, String gender, String email, String phoneNum, String verification, boolean isConfirmed)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNum = phoneNum;
        this.isConfirmed = isConfirmed;
        this.verification = verification;
        this.gender = gender;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
}
