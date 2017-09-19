package com.example.alin_.weatherforecast.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alin- on 16.09.2017.
 */

public class CityDBHelper extends SQLiteOpenHelper {
    public CityDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "savedCities.db";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + CityContract.CityEntry.TABLE_NAME + "(" +
                CityContract.CityEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CityContract.CityEntry.COLUMN_NAME + " TEXT  NOT NULL UNIQUE );";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
