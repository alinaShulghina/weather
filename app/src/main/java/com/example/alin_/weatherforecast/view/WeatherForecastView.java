package com.example.alin_.weatherforecast.view;

import com.example.alin_.weatherforecast.model.pojo.WeatherDay;
import com.example.alin_.weatherforecast.model.pojo.WeatherForecast;

/**
 * Created by alin- on 19.09.2017.
 */

public interface WeatherForecastView extends BaseView{
    void showToday(WeatherDay day);
    void showForecast(WeatherForecast forecast);
    String getCityName();
}
