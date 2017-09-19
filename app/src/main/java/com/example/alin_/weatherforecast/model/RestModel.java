package com.example.alin_.weatherforecast.model;

import com.example.alin_.weatherforecast.model.pojo.WeatherDay;
import com.example.alin_.weatherforecast.model.pojo.WeatherForecast;

import io.reactivex.Single;
import retrofit2.Call;

/**
 * Created by alin- on 18.09.2017.
 */

public interface RestModel {
    Call<WeatherDay> getToday(String cityName);
    Call<WeatherForecast> getForecast(String cityName);
}
