package com.example.alin_.weatherforecast.presenter;

import android.util.Log;

import com.example.alin_.weatherforecast.model.DBModel;
import com.example.alin_.weatherforecast.model.DBModelImpl;
import com.example.alin_.weatherforecast.model.RestModel;
import com.example.alin_.weatherforecast.model.RestModelImpl;
import com.example.alin_.weatherforecast.model.pojo.WeatherDay;
import com.example.alin_.weatherforecast.model.pojo.WeatherForecast;
import com.example.alin_.weatherforecast.view.activities.WeatherForecastView;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by alin- on 18.09.2017.
 */

public class WeatherForecastPresenterImpl implements WeatherForecastPresenter {
    private static final String TAG = "PresenterImpl";
    private WeatherForecastView view;
    private RestModel model = new RestModelImpl();
    private DBModel dbModel;
    private String name;

    public WeatherForecastPresenterImpl(WeatherForecastView view) {
        this.view = view;
        dbModel = new DBModelImpl(view.getViewContext());
    }

    @Override
    public void getWeatherForecast() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        //get weather for today
        Single<WeatherDay> weatherDay = model.getToday(view.getCityName())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        //get weather forecast
        Single<WeatherForecast> weatherForecast = model.getForecast(view.getCityName())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(
                weatherDay.subscribeWith(new DisposableSingleObserver<WeatherDay>() {
                    @Override
                    public void onSuccess(@NonNull WeatherDay weatherDay) {
                        name = weatherDay.getCity();
                        view.showToday(weatherDay);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }
                }));

        compositeDisposable.add(weatherForecast.subscribeWith(new DisposableSingleObserver<WeatherForecast>() {
            @Override
            public void onSuccess(@NonNull WeatherForecast weatherForecast) {
                view.showForecast(weatherForecast);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, e.getMessage());
            }
        }));

    }

    @Override
    public void onStop() {

    }

    @Override
    public void insertCity() {
        dbModel.insertCity(name);
    }
}
