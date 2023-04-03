package com.example.javacartproject;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartArray{
    public static ArrayList<Product> prods = new ArrayList<Product>();

    public static void add(Product product) {
        if (prods.contains(product)) {
            int quantity = quantities.get(product);
            quantities.put(product, quantity + 1);
        } else {
            prods.add(product);
            quantities.put(product, 1);
        }
        notifyListeners();
    }

    public static void delete(Product tmpProd) {
        prods.remove(tmpProd);
        quantities.remove(tmpProd);
        notifyListeners();
    }

    private static void notifyListeners() {
        for (OnCartUpdateListener listener : listeners) {
            listener.onCartUpdated();
        }
    }


    public static HashMap<Product, Integer> quantities;

    static {
        quantities = new HashMap<>();
    }

    public interface OnCartUpdateListener {
        void onCartUpdated();
    }

    private static List<OnCartUpdateListener> listeners = new ArrayList<>();

    public static void addOnCartUpdateListener(OnCartUpdateListener listener) {
        listeners.add(listener);
    }


}
