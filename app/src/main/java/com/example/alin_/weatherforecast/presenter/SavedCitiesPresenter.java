package com.example.alin_.weatherforecast.presenter;

import com.example.alin_.weatherforecast.model.pojo.City;

import java.util.List;

/**
 * Created by alin- on 19.09.2017.
 */

public interface SavedCitiesPresenter extends BasePresenter {
    List<City> getCitiesFromDB();
}
