package com.example.mobileproject.models;

public class Suit {
    String brand, name, gender, color, date;

    public Suit(String brand, String name, String gender, String color, String date)
    {
        this.brand = brand;
        this.name = name;
        this.gender = gender;
        this.color = color;
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public String getGender() {
        return gender;
    }

    public String getDate() {
        return date;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
