package com.example.alin_.weatherforecast.presenter;

/**
 * Created by alin- on 19.09.2017.
 */

public interface RecyclerViewPresenter extends BasePresenter {
    void deleteCityFromList(Integer id);

    void openWeatherForecastActivity(String name);
}
