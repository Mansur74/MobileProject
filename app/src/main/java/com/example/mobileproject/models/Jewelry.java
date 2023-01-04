package com.example.mobileproject.models;

public class Jewelry {
    String name, type, description, price, img_url;

    public Jewelry(String name, String type, String description, String price, String img_url) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.img_url = img_url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
