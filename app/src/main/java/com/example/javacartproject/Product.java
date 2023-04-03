package com.example.javacartproject;

import android.content.ContentValues;

import java.util.Objects;

public class Product {
    // Properties
    private String category;
    private String name;
    private double price;
    private int image;

    // Empty constructor
    public Product() {}

    // Full constructor
    public Product(String category, String name, double price, int image) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    // Getters
    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    // Setters
    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // equals method
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return image == product.image &&
                Objects.equals(category, product.category) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(category, name, price, image);
    }

    // Convert Product to ContentValues
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PRODUCT_COLUMN_CATEGORY, category);
        values.put(DatabaseHelper.PRODUCT_COLUMN_NAME, name);
        values.put(DatabaseHelper.PRODUCT_COLUMN_PRICE, price);
        values.put(DatabaseHelper.PRODUCT_COLUMN_IMAGE, image);
        return values;
    }
}
