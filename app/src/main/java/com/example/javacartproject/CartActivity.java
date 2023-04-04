package com.example.javacartproject;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class CartActivity extends MainActivity {
    private CartAdapter adapter;
    private TextView totalLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_list);

        ListView cartList = findViewById(R.id.cart_list);
        totalLabel = findViewById(R.id.total_label);

        adapter = new CartAdapter(this, R.layout.dynamic_cart_list, CartStaticArray.cartProducts, totalLabel);
        cartList.setAdapter(adapter);
    }
}
