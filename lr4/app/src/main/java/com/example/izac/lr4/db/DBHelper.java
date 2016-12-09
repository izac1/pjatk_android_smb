package com.example.izac.lr4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by izac on 03.12.16.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "place.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PLACE = "CREATE TABLE " + Place.TABLE  + "("
                + Place.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Place.KEY_name + " TEXT, "
                + Place.KEY_desc + " TEXT,"
                + Place.KEY_x + " DOUBLE,"
                + Place.KEY_y + " DOUBLE );";
        db.execSQL(CREATE_TABLE_PLACE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Place.TABLE);
        onCreate(db);

    }

}
