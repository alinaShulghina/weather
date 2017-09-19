package com.example.alin_.weatherforecast.model.data.api;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by alin- on 13.09.2017.
 */

public class WeatherAPI {
    public static String KEY = "d2a6b21c943e38d9e44edcc03c9912ad";
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public static ApiInterface getApiInterface() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build().create(ApiInterface.class);
    }

}

