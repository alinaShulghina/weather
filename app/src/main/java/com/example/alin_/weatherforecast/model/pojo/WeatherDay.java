package com.example.alin_.weatherforecast.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;

/**
 * Created by alin- on 13.09.2017.
 */

public class WeatherDay {
    public class WeatherTemp {
        Double temp;
        Double temp_min;
        Double temp_max;
    }

    public class WeatherDescription {
        String icon;
    }

    @SerializedName("main")
    private WeatherTemp temp;

    @SerializedName("weather")
    private List<WeatherDescription> desctiption;


    @SerializedName("name")
    private String city;

    @SerializedName("dt")
    private long timestamp;

    public WeatherDay(WeatherTemp temp, List<WeatherDescription> desctiption) {
        this.temp = temp;
        this.desctiption = desctiption;
    }

    public Calendar getDate() {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timestamp * 1000);
        return date;
    }

    public String getTemp() {
        return String.valueOf(convertKelvinToCelcius(temp.temp));
    }

    public String getTempMin() {
        return String.valueOf(convertKelvinToCelcius(temp.temp_min));
    }

    public String getTempMax() {
        return String.valueOf(convertKelvinToCelcius(temp.temp_max));
    }

    public String getTempInteger() {
        return String.valueOf(convertKelvinToCelcius(temp.temp.intValue()));
    }

    public String getTempWithDegree() {
        return String.valueOf(convertKelvinToCelcius(temp.temp.intValue())) + "\u00B0";
    }

    public String getCity() {
        return city;
    }

    public String getIcon() {
        return desctiption.get(0).icon;
    }

    public String getIconUrl() {
        return "http://openweathermap.org/img/w/" + desctiption.get(0).icon + ".png";
    }

    private int convertKelvinToCelcius(Double kelvin) {
        Double temp = kelvin - 273;
        return temp.intValue();
    }

    private int convertKelvinToCelcius(Integer faren) {
        return (faren - 273);
    }
}
