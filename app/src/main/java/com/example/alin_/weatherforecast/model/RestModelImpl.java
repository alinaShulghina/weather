package com.example.alin_.weatherforecast.model;

import com.example.alin_.weatherforecast.model.data.api.ApiInterface;
import com.example.alin_.weatherforecast.model.data.api.WeatherAPI;
import com.example.alin_.weatherforecast.model.pojo.WeatherDay;
import com.example.alin_.weatherforecast.model.pojo.WeatherForecast;

import io.reactivex.Single;
import retrofit2.Call;

/**
 * Created by alin- on 18.09.2017.
 */

public class RestModelImpl implements RestModel {
    ApiInterface apiInterface = WeatherAPI.getApiInterface();
    @Override
    public Call<WeatherDay> getToday(String cityName) {
        return apiInterface.getToday(cityName,WeatherAPI.KEY);
    }

    @Override
    public Call<WeatherForecast> getForecast(String cityName) {
        return apiInterface.getForecast(cityName,WeatherAPI.KEY);
    }
}
