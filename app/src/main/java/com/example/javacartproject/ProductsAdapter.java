package com.example.javacartproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductsAdapter extends ArrayAdapter<Product> {

    private final Context context;
    private final int resource;
    private final List<Product> products;

    public ProductsAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.products = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product tmpProd = products.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(resource, parent, false);
        }

        //Declare and Initialise UI items
        TextView prodName = convertView.findViewById(R.id.prod_name);
        TextView prodPrice = convertView.findViewById(R.id.prod_price);
        ImageView prodImg = convertView.findViewById(R.id.prod_img);
        Button addBtn = convertView.findViewById(R.id.add);
        TextView cartIndicator = convertView.findViewById(R.id.cart_indicator);

        // feed UI items
        prodName.setText(tmpProd.getName());
        prodPrice.setText(String.valueOf(tmpProd.getPrice()));
        prodImg.setImageResource(tmpProd.getImage());

        // if item is in cart show UI item cartIndicator, else do not show
        if (CartStaticArray.quantities.containsKey(tmpProd)) {
            cartIndicator.setVisibility(View.VISIBLE);
        } else {
            cartIndicator.setVisibility(View.GONE);
        }

        // Set the OnClickListener for the "Add" button
        // Add item to cart
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartStaticArray.add(tmpProd);
                //Toast.makeText(context, "Product added to cart", Toast.LENGTH_SHORT).show();
                cartIndicator.setVisibility(View.VISIBLE);
            }
        });
        return convertView;
    }
}
