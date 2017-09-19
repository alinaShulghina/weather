package com.example.alin_.weatherforecast.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alin_.weatherforecast.model.data.CityContract;
import com.example.alin_.weatherforecast.model.data.CityDBHelper;
import com.example.alin_.weatherforecast.model.pojo.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin- on 19.09.2017.
 */

public class DBModelImpl implements DBModel {
    private CityDBHelper dbHelper;
    public DBModelImpl(Context context){
        dbHelper = new CityDBHelper(context);
    }
    @Override
    public List<City> getCities() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(CityContract.CityEntry.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<City> cityList = new ArrayList<>();
        try {
            int idColumnIndex = cursor.getColumnIndex(CityContract.CityEntry._ID);
            int idRowIndex = cursor.getColumnIndex(CityContract.CityEntry.COLUMN_NAME);
            while (cursor.moveToNext()) {
                int currRowId = cursor.getInt(idColumnIndex);
                String name = cursor.getString(idRowIndex);
                cityList.add(new City(currRowId, name));
            }
        } finally {
            cursor.close();
        }
        return cityList;
    }

    @Override
    public void deleteCity(Integer id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(CityContract.CityEntry.TABLE_NAME, CityContract.CityEntry.COLUMN_ID + "=" + id, null);
    }

    @Override
    public void insertCity(String name) {
        ContentValues values = new ContentValues();
        values.put(CityContract.CityEntry.COLUMN_NAME, name);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(CityContract.CityEntry.TABLE_NAME, null, values);
    }

}

