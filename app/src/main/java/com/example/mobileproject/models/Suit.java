package com.example.mobileproject.models;

public class Suit {
    String brand, name, gender, color, price, description, img;

    public Suit(String brand, String name, String gender, String color, String price, String description, String img)
    {
        this.brand = brand;
        this.name = name;
        this.gender = gender;
        this.color = color;
        this.price = price;
        this.description = description;
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
