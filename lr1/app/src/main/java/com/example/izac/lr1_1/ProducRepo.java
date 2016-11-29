package com.example.izac.lr1_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by izac on 15.10.16.
 */

public class ProducRepo {
    private DBHelper dbHelper;

    public ProducRepo(Context context) {
        dbHelper = new DBHelper(context);
    }


    public int insert(Product product) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Product.KEY_name, product.name);
        values.put(Product.KEY_price,product.price);

        long product_Id = db.insert(Product.TABLE, null, values);
        db.close();
        return (int) product_Id;
    }

    public void delete(int student_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Product.TABLE, Product.KEY_ID + "= ?", new String[] { String.valueOf(student_Id) });
        db.close(); // Closing database connection
    }

    public void update(Product product) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Product.KEY_name, product.name);
        values.put(Product.KEY_price,product.price);

        db.update(Product.TABLE, values, Product.KEY_ID + "= ?", new String[] { String.valueOf(product.product_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getProductList() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Product.KEY_ID + "," +
                Product.KEY_name + "," +
                Product.KEY_price +
                " FROM " + Product.TABLE;


        ArrayList<HashMap<String, String>> productList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> product = new HashMap<String, String>();
                product.put("id", cursor.getString(cursor.getColumnIndex(Product.KEY_ID)));
                product.put("name", cursor.getString(cursor.getColumnIndex(Product.KEY_name)));
                product.put("price", cursor.getString(cursor.getColumnIndex(Product.KEY_price)));
                productList.add(product);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;

    }

    public Product getProductById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Product.KEY_ID + "," +
                Product.KEY_name + "," +
                Product.KEY_price +
                " FROM " + Product.TABLE
                + " WHERE " +
                Product.KEY_ID + "=?";

        int iCount =0;
        Product product = new Product();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                product.product_ID =cursor.getInt(cursor.getColumnIndex(Product.KEY_ID));
                product.name =cursor.getString(cursor.getColumnIndex(Product.KEY_name));
                product.price  =cursor.getDouble(cursor.getColumnIndex(Product.KEY_price));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return product;
    }


}
