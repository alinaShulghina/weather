package com.example.alin_.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.alin_.weatherforecast.controller.SavedCitiesFragment;
import com.example.alin_.weatherforecast.controller.WeatherForecastActivity;
import com.example.alin_.weatherforecast.model.City;

import java.util.List;

/**
 * Created by alin- on 16.09.2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {
    private List<City> citysList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageButton deleteButton;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.city_name);
            deleteButton = view.findViewById(R.id.delete_button);
        }
    }


    public CityAdapter(Context context, List<City> cityList) {
        this.citysList = cityList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_city_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final City city = citysList.get(position);
        holder.name.setText(city.getName());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavedCitiesFragment.deleteCityFromDataBase(city.getId());
                citysList.remove(position);
                notifyItemRemoved(position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WeatherForecastActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CityName", citysList.get(position).getName());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return citysList.size();
    }
}
