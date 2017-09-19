package com.example.alin_.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alin- on 13.09.2017.
 */

public class WeatherForecast {
    @SerializedName("list")
    private List<WeatherDay> items;

    public WeatherForecast(List<WeatherDay> items) {
        this.items = items;
    }

    public List<WeatherDay> getItems() {
        return items;
    }
}
