package com.example.mobileproject.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
        View currencyView = inflater.inflate(R.layout.list_item_dress ,null, true);

        TextView brand = (TextView) currencyView.findViewById(R.id.brand);
        TextView name = (TextView) currencyView.findViewById(R.id.name);
        TextView color = (TextView) currencyView.findViewById(R.id.color);
        TextView price = (TextView) currencyView.findViewById(R.id.price);
        TextView description = (TextView) currencyView.findViewById(R.id.description);

        ImageView imageView = (ImageView) currencyView.findViewById(R.id.img);

        Glide.with(getContext())
                .load(suitData.getImg())
                .override(300, 200)
                .into(imageView);

        brand.setText(suitData.getBrand());
        name.setText(suitData.getName());
        color.setText(suitData.getColor());
        price.setText(suitData.getPrice());
        description.setText(suitData.getDescription());

        return currencyView;
    }

}
