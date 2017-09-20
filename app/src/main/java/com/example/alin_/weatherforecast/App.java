package com.example.alin_.weatherforecast;

import android.app.Application;

/**
 * Created by alin- on 20.09.2017.
 */

public class App extends Application {
    private static App singleton;

    public static App getInstance() {
        return singleton;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
