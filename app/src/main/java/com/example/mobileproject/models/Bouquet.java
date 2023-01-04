package com.example.mobileproject.models;

public class Bouquet {

    String name, description, price, img_url;

    public Bouquet(String name, String description, String price, String img_url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
