package com.example.alin_.weatherforecast.model.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by alin- on 16.09.2017.
 */

public final class CityContract {
    public static final String CONTENT_AUTHORITY = "com.example.alin-.weatherapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_CITIES = "cities";
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_CITIES);
    private CityContract(){}
    public static class CityEntry implements BaseColumns {
        public final static String TABLE_NAME = "Cities";
        public final static String COLUMN_ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "Name";
        public final static String COLUMN_COUNTRY = "Country";
    }
}
