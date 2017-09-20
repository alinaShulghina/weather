package com.example.alin_.weatherforecast.model;

import com.example.alin_.weatherforecast.model.pojo.WeatherDay;
import com.example.alin_.weatherforecast.model.pojo.WeatherForecast;

import io.reactivex.Single;

/**
 * Created by alin- on 18.09.2017.
 */

public interface RestModel {
    Single<WeatherDay> getToday(String cityName);

    Single<WeatherForecast> getForecast(String cityName);
}
