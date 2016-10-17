package com.example.izac.lr1_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by izac on 15.10.16.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;


    private static final String DATABASE_NAME = "product.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Product.TABLE  + "("
                + Product.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Product.KEY_name + " TEXT, "
                + Product.KEY_price + " DOUBLE )";
        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Product.TABLE);
        onCreate(db);

    }
}
