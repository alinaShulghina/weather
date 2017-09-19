package com.example.alin_.weatherforecast.controller;


import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.alin_.weatherforecast.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;


/**
 * Created by alin- on 13.09.2017.
 */

public class SearchFragment extends Fragment {
    private View rootView;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.search_fragment, container, false);
        SupportPlaceAutocompleteFragment autocompleteFragment = (SupportPlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setHint("Find your city");
        AutocompleteFilter filter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();
        autocompleteFragment.setFilter(filter);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Intent intent = new Intent(getContext(), WeatherForecastActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CityName", place.getName().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onError(Status status) {
                System.out.println("An error occurred: " + status);
            }
        });

        return rootView;
    }

}



