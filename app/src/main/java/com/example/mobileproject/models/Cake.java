package com.example.mobileproject.models;

public class Cake {

    String name, ingredients, layer, price, img;
    public Cake(String name, String ingredients, String layer, String price, String img)
    {
        this.name = name;
        this.ingredients = ingredients;
        this.layer = layer;
        this.price = price;
        this.img = img;

    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getLayer() {
        return layer;
    }

    public String getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
