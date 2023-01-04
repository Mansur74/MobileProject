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
import com.example.mobileproject.models.Jewelry;

import java.util.List;

public class JewelryAdapter extends ArrayAdapter<Jewelry> {

    private final LayoutInflater inflater;
    Activity ctx;

    public JewelryAdapter(Activity context, List<Jewelry> views) {
        super(context, 0, views);
        inflater = LayoutInflater.from(context);
        ctx = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Jewelry jewelryData = getItem(position);
        View currencyView = inflater.inflate(R.layout.list_item_jewelry ,null, true);

        TextView name = (TextView) currencyView.findViewById(R.id.name);
        TextView description = (TextView) currencyView.findViewById(R.id.description);
        TextView type = (TextView) currencyView.findViewById(R.id.type);
        TextView price = (TextView) currencyView.findViewById(R.id.price);
        ImageView imageView = (ImageView) currencyView.findViewById(R.id.jewelry_img);

        Glide.with(getContext())
                .load(jewelryData.getImg_url())
                .override(300, 200)
                .into(imageView);

        name.setText(jewelryData.getName());
        description.setText(jewelryData.getDescription());
        type.setText(jewelryData.getType());
        price.setText(jewelryData.getPrice());

        return currencyView;
    }
}