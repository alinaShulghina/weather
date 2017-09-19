package com.example.alin_.weatherforecast.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.alin_.weatherforecast.presenter.SavedCitiesPresenter;
import com.example.alin_.weatherforecast.presenter.SavedSitiesPresenterImpl;
import com.example.alin_.weatherforecast.view.BaseView;
import com.example.alin_.weatherforecast.view.SavedSitiesView;
import com.example.alin_.weatherforecast.view.adapters.RecyclerViewAdapter;
import com.example.alin_.weatherforecast.R;
import com.example.alin_.weatherforecast.model.pojo.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin- on 13.09.2017.
 */

public class SavedCitiesFragment extends Fragment implements SavedSitiesView {
    private List<City> cityList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private SavedCitiesPresenter presenter;
    public SavedCitiesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_cities_fragment, container, false);
        presenter = new SavedSitiesPresenterImpl(this);
        recyclerView = view.findViewById(R.id.rv);
        cityList = presenter.getCitiesFromDB();
        mAdapter = new RecyclerViewAdapter(getContext(), cityList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
    @Override
    public void showError(){

    }
    @Override
    public Context getViewContext(){
        return this.getContext();
    }


    public static SavedCitiesFragment newInstance(){
        return new SavedCitiesFragment();
    }

}
