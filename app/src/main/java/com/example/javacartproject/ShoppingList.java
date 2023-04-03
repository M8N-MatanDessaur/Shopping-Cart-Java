package com.example.javacartproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingList extends MainActivity {
    // Declare variables
    ArrayList<Product> prodArray;
    ProductsAdapter adapter;
    TextView categoryTitle;
    ListView prodList;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list);

        // Initialize variables
        categoryTitle = findViewById(R.id.category_title);
        prodList = findViewById(R.id.prod_list);
        prodArray = new ArrayList<>();

        // Get the intent Extras (Database name and item-category)
        Intent intent = getIntent();
        String selectedCategory = intent.getStringExtra("category");
        String databaseName = intent.getStringExtra("databaseName");

        // Open database with databaseName
        db = SQLiteDatabase.openDatabase(
                getApplicationContext().getDatabasePath(databaseName).getAbsolutePath(),
                null,
                SQLiteDatabase.OPEN_READWRITE
        );

        // Set the page title with the selected item category
        categoryTitle.setText(selectedCategory);

        // Initialize List Adapter and set it to be the adapter to the product list
        adapter = new ProductsAdapter(this, R.layout.dynamic_product_list, prodArray);
        prodList.setAdapter(adapter);

        // Load products from the database
        loadProductsFromDatabase(selectedCategory);
    }

    // Load products from database with the selected category
    private void loadProductsFromDatabase(String selectedCategory) {

        // Define the columns to retrieve (Select ALL)
        String[] projection = {
                DatabaseHelper.PRODUCT_COLUMN_CATEGORY,
                DatabaseHelper.PRODUCT_COLUMN_NAME,
                DatabaseHelper.PRODUCT_COLUMN_PRICE,
                DatabaseHelper.PRODUCT_COLUMN_IMAGE
        };

        // Define the WHERE clause [Where Category = selectedCategory]
        String selection = DatabaseHelper.PRODUCT_COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = {selectedCategory};

        // Perform the query on PRODUCT_TABLE_NAME
        try (Cursor cursor = db.query(DatabaseHelper.PRODUCT_TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null)) {

            // Iterate over the query results and add the products to the array
            while (cursor.moveToNext()) {
                String prodCategory = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUCT_COLUMN_CATEGORY));
                String prodName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUCT_COLUMN_NAME));
                double prodPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUCT_COLUMN_PRICE));
                int prodImage = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUCT_COLUMN_IMAGE));

                // Create a new Product instance and add it to the prodArray
                prodArray.add(new Product(prodCategory, prodName, prodPrice, prodImage));
            }
        }
    }
}
