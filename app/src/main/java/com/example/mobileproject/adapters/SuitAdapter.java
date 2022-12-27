package com.example.mobileproject.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.models.Suit;

import java.util.List;

public class SuitAdapter extends ArrayAdapter<Suit> {

    private final LayoutInflater inflater;

    public SuitAdapter(Activity context, List<Suit> views) {
        super(context, 0, views);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Suit suitData = getItem(position);
        View currencyView = inflater.inflate(R.layout.list_item_suit ,null, true);

        TextView brand = (TextView) currencyView.findViewById(R.id.brand);
        TextView name = (TextView) currencyView.findViewById(R.id.name);
        TextView gender = (TextView) currencyView.findViewById(R.id.gender);
        TextView color = (TextView) currencyView.findViewById(R.id.color);
        TextView date = (TextView) currencyView.findViewById(R.id.date);

        brand.setText(suitData.getBrand());
        name.setText(suitData.getName());
        gender.setText(suitData.getGender());
        color.setText(suitData.getColor());
        date.setText(suitData.getDate());

        return currencyView;
    }

}
