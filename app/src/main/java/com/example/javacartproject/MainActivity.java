package com.example.javacartproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // Declare
    ProductTableDataGateway dataGateway;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of ProductTableDataGateway class & get a Read/Write Database
        dataGateway = new ProductTableDataGateway(this);
        db = dataGateway.getWritableDatabase();

        // Resets & Seed Database
        db.delete(ProductTableDataGateway.PRODUCT_TABLE_NAME, null, null);
        SeedDatabase();
    }

    // Create the three dots menu with home_button and the products categories
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate main_menu.xml to add [Home] & [Cart] items
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Query the database to get distinct categories (All categories)
        Cursor cursor = db.query(true, ProductTableDataGateway.PRODUCT_TABLE_NAME, new String[]{ProductTableDataGateway.PRODUCT_COLUMN_CATEGORY},
                null, null, null, null, null, null);

        // Iterate through the cursor and add distinct categories to the menu as items
        int index = 0;
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex(ProductTableDataGateway.PRODUCT_COLUMN_CATEGORY));
            menu.add(Menu.NONE, Menu.FIRST + index, index, category);
            index++;
        }

        // Close the cursor after use
        cursor.close();
        return true;
    }

    // Selected menu-item click listener
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            // Home
            case R.id.home_button:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            // Cart
            case R.id.cart_button:
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;

            // Any selected categories
            // add the item title/category and the database name in extra
            default:
                intent = new Intent(this, ProductsListActivity.class);
                intent.putExtra("category", item.getTitle());
                intent.putExtra("databaseName", dataGateway.getDatabaseName());
                startActivity(intent);
                break;
        }
        return true;
    }

    // Function to Fill the database with products
    public void SeedDatabase() {
        // Create an array of all products
        Product[] products = {
                new Product("Fruits-&-Vegetable", "Apple", 0.99, R.drawable.apple),
                new Product("Fruits-&-Vegetable", "Banana", 0.49, R.drawable.banana),
                new Product("Fruits-&-Vegetable", "Carrot", 0.29, R.drawable.carrot),
                new Product("Fruits-&-Vegetable", "Lettuce", 0.89, R.drawable.lettuce),
                new Product("Dairy", "Milk", 2.99, R.drawable.milk),
                new Product("Dairy", "Cheese", 3.99, R.drawable.cheese),
                new Product("Dairy", "Creamer", 3.99, R.drawable.creamer),
                new Product("Meat", "Beef", 6.99, R.drawable.beef),
                new Product("Meat", "Pork", 4.99, R.drawable.pork),
                new Product("Meat", "Chicken", 3.99, R.drawable.chicken),
                new Product("Meat", "Fish", 5.99, R.drawable.fish),
                new Product("hygiene", "Body soap", 3.99, R.drawable.pinksoap),
                new Product("hygiene", "Shampoo", 3.99, R.drawable.shampoo),
                new Product("hygiene", "Deodorant", 3.99, R.drawable.deo),
                new Product("hygiene", "Purel", 3.99, R.drawable.purel)
        };

        // Insert the products into the database
        for (Product product : products) {
            ContentValues values = product.getContentValues();
            db.insert(ProductTableDataGateway.PRODUCT_TABLE_NAME, null, values);
        }
    }

    // Go to cart button
    public void to_cart(View view) {
        Intent intent;
        intent = new Intent (this, CartActivity.class);
        startActivity(intent);
    }
}