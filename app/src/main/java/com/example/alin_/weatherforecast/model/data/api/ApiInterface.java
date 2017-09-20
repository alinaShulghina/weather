package com.example.alin_.weatherforecast.model.data.api;

import com.example.alin_.weatherforecast.model.pojo.WeatherDay;
import com.example.alin_.weatherforecast.model.pojo.WeatherForecast;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alin- on 18.09.2017.
 */

public interface ApiInterface {
    @GET("weather")
    Single<WeatherDay> getToday(
            @Query("q") String name,
            @Query("appid") String appid
    );

    @GET("forecast")
    Single<WeatherForecast> getForecast(
            @Query("q") String name,
            @Query("appid") String appid
    );
}
