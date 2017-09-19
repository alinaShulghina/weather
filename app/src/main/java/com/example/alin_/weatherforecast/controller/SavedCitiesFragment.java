package com.example.alin_.weatherforecast.controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.alin_.weatherforecast.CityAdapter;
import com.example.alin_.weatherforecast.R;
import com.example.alin_.weatherforecast.data.CityContract;
import com.example.alin_.weatherforecast.data.CityDBHelper;
import com.example.alin_.weatherforecast.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin- on 13.09.2017.
 */

public class SavedCitiesFragment extends Fragment {
    public static CityDBHelper dbHelper;
    private List<City> cityList;
    private RecyclerView recyclerView;
    private CityAdapter mAdapter;

    public SavedCitiesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_cities_fragment, container, false);
        recyclerView = view.findViewById(R.id.rv);
        cityList = new ArrayList<>();
        mAdapter = new CityAdapter(getContext(), cityList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        dbHelper = new CityDBHelper(getContext());
        displayDatabaseInfo();
        return view;
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(CityContract.CityEntry.TABLE_NAME, null, null, null, null, null, null);
        try {
            int idColumnIndex = cursor.getColumnIndex(CityContract.CityEntry._ID);
            int idRowIndex = cursor.getColumnIndex(CityContract.CityEntry.COLUMN_NAME);
            while (cursor.moveToNext()) {
                int currRowId = cursor.getInt(idColumnIndex);
                String name = cursor.getString(idRowIndex);
                cityList.add(new City(currRowId, name));
            }
            mAdapter.notifyDataSetChanged();
        } finally {
            cursor.close();
        }
    }

    public static void deleteCityFromDataBase(Integer id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(CityContract.CityEntry.TABLE_NAME, CityContract.CityEntry.COLUMN_ID + "=" + id, null);
    }
}
