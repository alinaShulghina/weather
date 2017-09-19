package com.example.alin_.weatherforecast.presenter;

import android.util.Log;

import com.example.alin_.weatherforecast.model.DBModel;
import com.example.alin_.weatherforecast.model.DBModelImpl;
import com.example.alin_.weatherforecast.model.RestModelImpl;
import com.example.alin_.weatherforecast.model.RestModel;
import com.example.alin_.weatherforecast.model.pojo.WeatherDay;
import com.example.alin_.weatherforecast.model.pojo.WeatherForecast;
import com.example.alin_.weatherforecast.view.WeatherForecastView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by alin- on 18.09.2017.
 */

public class WeatherForecastPresenterImpl implements WeatherForecastPresenter {
    private static final String TAG = "PresenterImpl";
    private WeatherForecastView view;
    private RestModel model = new RestModelImpl();
    private DBModel dbModel;
    public WeatherForecastPresenterImpl(WeatherForecastView view) {
        this.view = view;
        dbModel = new DBModelImpl(view.getViewContext());
    }

    @Override
    public void getWeatherForecast() {
        // get weather for today
        Call<WeatherDay> callToday = model.getToday(view.getCityName());
        callToday.enqueue(new Callback<WeatherDay>() {
            @Override
            public void onResponse(Call<WeatherDay> call, Response<WeatherDay> response) {
                Log.e(TAG, "onResponse");
                WeatherDay data = response.body();
                Log.d(TAG, response.toString());

                if (response.isSuccessful()) {
                    view.showToday(data);
                }

            }

            @Override
            public void onFailure(Call<WeatherDay> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, t.toString());
            }
        });

        // get weather forecast
        Call<WeatherForecast> callForecast = model.getForecast(view.getCityName());
        callForecast.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                Log.e(TAG, "onResponse");
                WeatherForecast data = response.body();
                Log.d(TAG, response.toString());

                if (response.isSuccessful()) {
                    view.showForecast(data);

                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void onStop() {

    }

    @Override
    public void insertCity() {
        dbModel.insertCity(view.getCityName());
    }
}
