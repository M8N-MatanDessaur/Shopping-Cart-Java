package com.example.javacartproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductTableDataGateway extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shopping_cart.db";
    private static final int DATABASE_VERSION = 1;

    // Define the product table model
    public static final String PRODUCT_TABLE_NAME = "products";
    public static final String PRODUCT_COLUMN_ID = "_id";
    public static final String PRODUCT_COLUMN_CATEGORY = "category";
    public static final String PRODUCT_COLUMN_NAME = "name";
    public static final String PRODUCT_COLUMN_PRICE = "price";
    public static final String PRODUCT_COLUMN_IMAGE = "image";

    public static final String PRODUCT_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + PRODUCT_TABLE_NAME + " (" +
                    PRODUCT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PRODUCT_COLUMN_CATEGORY + " INTEGER, " +
                    PRODUCT_COLUMN_NAME + " TEXT, " +
                    PRODUCT_COLUMN_PRICE + " REAL, " +
                    PRODUCT_COLUMN_IMAGE + " TEXT)";

    public ProductTableDataGateway(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PRODUCT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        onCreate(db);
    }
}

