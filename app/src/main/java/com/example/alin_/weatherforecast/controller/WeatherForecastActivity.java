package com.example.alin_.weatherforecast.controller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alin_.weatherforecast.R;
import com.example.alin_.weatherforecast.data.CityContract;
import com.example.alin_.weatherforecast.data.CityDBHelper;
import com.example.alin_.weatherforecast.WeatherAPI;
import com.example.alin_.weatherforecast.model.WeatherDay;
import com.example.alin_.weatherforecast.model.WeatherForecast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherForecastActivity extends AppCompatActivity {

    private String TAG = "Weather";
    private CityDBHelper dbHelper = new CityDBHelper(this);
    private WeatherAPI.ApiInterface api;
    private String city;
    /*
    Views declaration
     */
    private TextView tvTemp;
    private TextView tvCityName;
    private ImageView tvImage;
    private LinearLayout llForecast;
    private TextView tvDateToday;
    private TextView tvMinTemp;
    private TextView tvMaxTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_forecast_activity);
        api = WeatherAPI.getClient().create(WeatherAPI.ApiInterface.class);
        tvTemp = findViewById(R.id.tvTemp);
        tvImage = findViewById(R.id.weather_icon);
        llForecast = findViewById(R.id.weather_daily_list);
        tvDateToday = findViewById(R.id.current_date);
        tvCityName = findViewById(R.id.city_country);
        tvMinTemp = findViewById(R.id.minTemp);
        tvMaxTemp = findViewById(R.id.maxTemp);
        city = getIntent().getStringExtra("CityName");
        getWeather(llForecast, city);
    }

    public void getWeather(final View view, String zipCode) {
        String key = WeatherAPI.KEY;
        final SimpleDateFormat currentDateFormat = new SimpleDateFormat("EEE, MMM d");
        Log.d(TAG, "OK");
        // get weather for today
        Call<WeatherDay> callToday = api.getToday(zipCode, key);
        callToday.enqueue(new Callback<WeatherDay>() {
            @Override
            public void onResponse(Call<WeatherDay> call, Response<WeatherDay> response) {
                Log.e(TAG, "onResponse");
                WeatherDay data = response.body();
                Log.d(TAG, response.toString());

                if (response.isSuccessful()) {
                    city = data.getCity();
                    tvCityName.setText(data.getCity());
                    tvDateToday.setText(currentDateFormat.format(data.getDate().getTime()));
                    tvTemp.setText(data.getTempWithDegree());
                    Glide.with(WeatherForecastActivity.this).load(data.getIconUrl()).into(tvImage);
                    tvMinTemp.setText(data.getTempMin());
                    tvMaxTemp.setText(data.getTempMax());
                }

            }

            @Override
            public void onFailure(Call<WeatherDay> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, t.toString());
            }
        });

        // get weather forecast
        Call<com.example.alin_.weatherforecast.model.WeatherForecast> callForecast = api.getForecast(zipCode, key);
        callForecast.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<com.example.alin_.weatherforecast.model.WeatherForecast> call, Response<com.example.alin_.weatherforecast.model.WeatherForecast> response) {
                Log.e(TAG, "onResponse");
                com.example.alin_.weatherforecast.model.WeatherForecast data = response.body();
                Log.d(TAG, response.toString());

                if (response.isSuccessful()) {
                    final SimpleDateFormat formatDayOfWeek = new SimpleDateFormat("E");
                    llForecast.removeAllViews();
                    for (WeatherDay day : data.getItems()) {
                        //choose middle temperature of the day
                        if ((day.getDate().get(Calendar.HOUR_OF_DAY)) == 15) {
                            Log.d(TAG, day.getTempInteger());
                            Log.d(TAG, "---");

                            LinearLayout item = new LinearLayout(WeatherForecastActivity.this);
                            item.setOrientation(LinearLayout.VERTICAL);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            params.weight = 1.0f;
                            params.gravity = Gravity.CENTER_VERTICAL;
                            item.setLayoutParams(params);

                            // show day of week
                            TextView tvDay = new TextView(WeatherForecastActivity.this);
                            String dayOfWeek = formatDayOfWeek.format(day.getDate().getTime());
                            tvDay.setText(dayOfWeek);


                            // show image
                            ImageView ivIcon = new ImageView(WeatherForecastActivity.this);
                            Glide.with(WeatherForecastActivity.this).load(day.getIconUrl()).into(ivIcon);

                            // show temp
                            TextView tvTemp = new TextView(WeatherForecastActivity.this);
                            tvTemp.setText(day.getTempWithDegree());
                            item.addView(tvDay);
                            item.addView(ivIcon);
                            item.addView(tvTemp);
                            llForecast.addView(item);
                        }
                    }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save_city:
                insertData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void insertData() {
        ContentValues values = new ContentValues();
        values.put(CityContract.CityEntry.COLUMN_NAME, city);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(CityContract.CityEntry.TABLE_NAME, null, values);
        Toast.makeText(getApplicationContext(), "City is saved!", Toast.LENGTH_LONG).show();
    }

}
