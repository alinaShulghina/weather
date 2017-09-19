package com.example.alin_.weatherforecast.model;

import com.example.alin_.weatherforecast.model.pojo.City;

import java.util.List;

/**
 * Created by alin- on 19.09.2017.
 */

public interface DBModel {
    List<City> getCities();
    void insertCity(String name);
    void deleteCity(Integer id);
}
