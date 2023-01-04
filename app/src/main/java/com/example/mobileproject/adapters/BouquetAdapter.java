package com.example.mobileproject.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.models.Bouquet;
import com.example.mobileproject.models.Cake;

import java.util.List;

public class BouquetAdapter extends ArrayAdapter<Bouquet> {

    private final LayoutInflater inflater;
    Activity ctx;

    public BouquetAdapter(Activity context, List<Bouquet> views) {
        super(context, 0, views);
        inflater = LayoutInflater.from(context);
        ctx = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Bouquet bouquetData = getItem(position);
        View currencyView = inflater.inflate(R.layout.list_item_bouquet ,null, true);

        TextView name = (TextView) currencyView.findViewById(R.id.name);
        TextView description = (TextView) currencyView.findViewById(R.id.description);
        TextView price = (TextView) currencyView.findViewById(R.id.price);
        ImageView imageView = (ImageView) currencyView.findViewById(R.id.bouquet_img);

        Glide.with(getContext())
                .load(bouquetData.getImg_url())
                .override(300, 200)
                .into(imageView);

        name.setText(bouquetData.getName());
        description.setText(bouquetData.getDescription());
        price.setText(bouquetData.getPrice());

        return currencyView;
    }
}