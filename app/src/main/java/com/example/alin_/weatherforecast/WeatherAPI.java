package com.example.alin_.weatherforecast;


import com.example.alin_.weatherforecast.model.WeatherDay;
import com.example.alin_.weatherforecast.model.WeatherForecast;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alin- on 13.09.2017.
 */

public class WeatherAPI {
        public static String KEY = "d2a6b21c943e38d9e44edcc03c9912ad";
        public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
        private static Retrofit retrofit = null;

        public interface ApiInterface {
            @GET("weather")
            Call<WeatherDay> getToday(
                    @Query("q") String name,
                    @Query("appid") String appid
            );

            @GET("forecast")
            Call<WeatherForecast> getForecast(
                    @Query("q") String name,
                    @Query("appid") String appid
            );
        }

        public static Retrofit getClient() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
}
