package com.example.lab5_starter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {

    private final Context context;
    private final ArrayList<City> cities;

    public CityArrayAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
        this.context = context;
        this.cities = cities;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_city, parent, false);
        }

        TextView cityName = convertView.findViewById(R.id.textCityName);
        TextView provinceName = convertView.findViewById(R.id.textProvinceName);
        Button deleteButton = convertView.findViewById(R.id.buttonDelete);

        if (city != null) {
            cityName.setText(city.getName());
            provinceName.setText(city.getProvince());
        }

        // Handle delete click
        deleteButton.setOnClickListener(v -> {
            if (context instanceof MainActivity) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete City")
                        .setMessage("Are you sure you want to delete " + city.getName() + "?")
                        .setPositiveButton("Delete", (dialog, which) -> {
                            ((MainActivity) context).deleteCity(city);
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });

        return convertView;
    }
}
