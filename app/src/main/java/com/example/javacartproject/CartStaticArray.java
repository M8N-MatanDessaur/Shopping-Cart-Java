package com.example.javacartproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartStaticArray {
    // Static Array for Products that are in the cart
    public static ArrayList<Product> cartProducts = new ArrayList<Product>();

    // Add to cart (checks if already in cart)
    // if already in cart, change quantity, else Add to cart
    public static void add(Product product) {
        if (cartProducts.contains(product)) {
            int quantity = quantities.get(product);
            quantities.put(product, quantity + 1);
        } else {
            cartProducts.add(product);
            quantities.put(product, 1);
        }
    }

    // Delete/Remove from cart & quantities
    public static void delete(Product tmpProd) {
        cartProducts.remove(tmpProd);
        quantities.remove(tmpProd);
    }

    // HashMap method
    // Why  : Keeps track of the quantity of each product in the cart [no duplicates]
    // Uses : Stores the quantity of each product in the cart
    public static HashMap<Product, Integer> quantities;
    static {quantities = new HashMap<>();}
}
