package com.example.alin_.weatherforecast.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.alin_.weatherforecast.model.DBModel;
import com.example.alin_.weatherforecast.model.DBModelImpl;
import com.example.alin_.weatherforecast.view.WeatherForecastActivity;
import com.example.alin_.weatherforecast.view.adapters.IRecyclerViewAdapter;
import com.example.alin_.weatherforecast.view.adapters.RecyclerViewAdapter;

/**
 * Created by alin- on 19.09.2017.
 */

public class RecyclerViewPresenterImpl implements RecyclerViewPresenter {
    private IRecyclerViewAdapter adapter;
    private DBModel model;
    private Context context;
    public RecyclerViewPresenterImpl(RecyclerViewAdapter adapter){
        this.adapter = adapter;
        context = adapter.getContext();
        model = new DBModelImpl(context);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void openWeatherForecastActivity(String name) {
        Intent intent = new Intent(context, WeatherForecastActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("CityName", name);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void deleteCityFromList(Integer id) {
        model.deleteCity(id);
    }
}
