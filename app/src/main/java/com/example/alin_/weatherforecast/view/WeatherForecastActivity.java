package com.example.alin_.weatherforecast.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alin_.weatherforecast.R;
import com.example.alin_.weatherforecast.model.pojo.WeatherDay;
import com.example.alin_.weatherforecast.model.pojo.WeatherForecast;
import com.example.alin_.weatherforecast.presenter.WeatherForecastPresenter;
import com.example.alin_.weatherforecast.presenter.WeatherForecastPresenterImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeatherForecastActivity extends AppCompatActivity implements WeatherForecastView {
    private WeatherForecastPresenter presenter;
    private String TAG = "Weather";
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
        tvTemp = findViewById(R.id.tvTemp);
        tvImage = findViewById(R.id.weather_icon);
        llForecast = findViewById(R.id.weather_daily_list);
        tvDateToday = findViewById(R.id.current_date);
        tvCityName = findViewById(R.id.city_country);
        tvMinTemp = findViewById(R.id.minTemp);
        tvMaxTemp = findViewById(R.id.maxTemp);
        city = getIntent().getStringExtra("CityName");
        presenter = new WeatherForecastPresenterImpl(this);
        presenter.getWeatherForecast();
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
                presenter.insertCity();
                Toast.makeText(getApplicationContext(), "City is saved!", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public String getCityName() {
        return city;
    }

    @Override
    public void showError() {

    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void showToday(WeatherDay data) {
        final SimpleDateFormat currentDateFormat = new SimpleDateFormat("EEE, MMM d");
        tvCityName.setText(data.getCity());
        tvDateToday.setText(currentDateFormat.format(data.getDate().getTime()));
        tvTemp.setText(data.getTempWithDegree());
        Glide.with(WeatherForecastActivity.this).load(data.getIconUrl()).into(tvImage);
        tvMinTemp.setText(data.getTempMin());
        tvMaxTemp.setText(data.getTempMax());
    }

    @Override
    public void showForecast(WeatherForecast data) {
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
