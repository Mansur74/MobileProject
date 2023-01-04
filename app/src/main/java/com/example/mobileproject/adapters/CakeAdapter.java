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
import com.example.mobileproject.models.Cake;
import com.example.mobileproject.models.Dress;

import java.util.List;

public class CakeAdapter extends ArrayAdapter<Cake> {

    private final LayoutInflater inflater;
    Activity ctx;

    public CakeAdapter(Activity context, List<Cake> views) {
        super(context, 0, views);
        inflater = LayoutInflater.from(context);
        ctx = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cake cakeData = getItem(position);
        View currencyView = inflater.inflate(R.layout.list_item_cake ,null, true);

        TextView name = (TextView) currencyView.findViewById(R.id.name);
        TextView ingredients = (TextView) currencyView.findViewById(R.id.ingredients);
        TextView layer = (TextView) currencyView.findViewById(R.id.layer);
        TextView price = (TextView) currencyView.findViewById(R.id.price);
        ImageView imageView = (ImageView) currencyView.findViewById(R.id.cake_img);

        Glide.with(getContext())
                .load(cakeData.getImg())
                .override(300, 200)
                .into(imageView);

        name.setText(cakeData.getName());
        ingredients.setText(cakeData.getIngredients());
        layer.setText(cakeData.getLayer());
        price.setText(cakeData.getPrice());

        return currencyView;
    }
}
