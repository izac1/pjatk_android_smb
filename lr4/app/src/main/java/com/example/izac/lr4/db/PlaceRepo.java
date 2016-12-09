package com.example.izac.lr4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by izac on 03.12.16.
 */

public class PlaceRepo {
    private DBHelper dbHelper;

    public PlaceRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Place place) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Place.KEY_name, place.name);
        values.put(Place.KEY_desc,place.desc);
        values.put(Place.KEY_x,place.x);
        values.put(Place.KEY_y,place.y);

        long place_Id = db.insert(Place.TABLE, null, values);
        db.close();
        return (int) place_Id;
    }

    public void delete(int place_id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Place.TABLE, Place.KEY_ID + "= ?", new String[] { String.valueOf(place_id) });
        db.close();
    }

    public ArrayList<HashMap<String, String>> getProductList() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Place.KEY_ID + "," +
                Place.KEY_name + "," +
                Place.KEY_desc + " , "+
                Place.KEY_x + " , "+
                Place.KEY_y +
                " FROM " + Place.TABLE;


        ArrayList<HashMap<String, String>> productList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> product = new HashMap<String, String>();
                product.put("id", cursor.getString(cursor.getColumnIndex(Place.KEY_ID)));
                product.put("name", cursor.getString(cursor.getColumnIndex(Place.KEY_name)));
                product.put("desc", cursor.getString(cursor.getColumnIndex(Place.KEY_desc)));
                product.put("x", cursor.getString(cursor.getColumnIndex(Place.KEY_x)));
                product.put("y", cursor.getString(cursor.getColumnIndex(Place.KEY_y)));
                productList.add(product);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;

    }

}
