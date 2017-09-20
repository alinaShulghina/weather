package com.example.alin_.weatherforecast.presenter;

import com.example.alin_.weatherforecast.model.DBModel;
import com.example.alin_.weatherforecast.model.DBModelImpl;
import com.example.alin_.weatherforecast.model.pojo.City;
import com.example.alin_.weatherforecast.view.BaseView;

import java.util.List;

/**
 * Created by alin- on 19.09.2017.
 */

public class SavedSitiesPresenterImpl implements SavedCitiesPresenter {
    private BaseView view;
    private DBModel model;

    public SavedSitiesPresenterImpl(BaseView view) {
        this.view = view;
        model = new DBModelImpl(view.getViewContext());
    }

    @Override
    public List<City> getCitiesFromDB() {
        return model.getCities();
    }

    @Override
    public void onStop() {

    }
}
