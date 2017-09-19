package com.example.alin_.weatherforecast.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.alin_.weatherforecast.R;
import com.example.alin_.weatherforecast.model.pojo.City;
import com.example.alin_.weatherforecast.presenter.RecyclerViewPresenter;
import com.example.alin_.weatherforecast.presenter.RecyclerViewPresenterImpl;

import java.util.List;

/**
 * Created by alin- on 16.09.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements IRecyclerViewAdapter {
    private List<City> citysList;
    private RecyclerViewPresenter presenter;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageButton deleteButton;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.city_name);
            deleteButton = view.findViewById(R.id.delete_button);
            context = view.getContext();
        }
    }

    public Context getContext() {
        return context;
    }

    public RecyclerViewAdapter(Context context, List<City> cityList) {
        this.citysList = cityList;
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_city_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final City city = citysList.get(position);
        presenter = new RecyclerViewPresenterImpl(this);
        holder.name.setText(city.getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openWeatherForecastActivity(city.getName());
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteCityFromList(city.getId());
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return citysList.size();
    }
}
