package com.example.mobileproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileproject.R;
import com.example.mobileproject.adapters.DressAdapter;
import com.example.mobileproject.adapters.SuitAdapter;
import com.example.mobileproject.models.Dress;
import com.example.mobileproject.models.Suit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DressActivity extends AppCompatActivity {

    ListView dressList;
    List<Dress> dresses;
    DressAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress);

        dressList = findViewById(R.id.dress_list);
        dresses = new ArrayList<>();

        adapter = new DressAdapter(DressActivity.this, dresses);
        dressList.setAdapter(adapter);

        toolbar = findViewById(R.id.toolbar);
        ImageButton backButton = toolbar.findViewById(R.id.back);
        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("Dresses");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.jsonkeeper.com/b/W8LV";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for(int i=0; i <  response.getJSONArray("dresses").length(); i++)
                            {
                                JSONObject object = response.getJSONArray("dresses").getJSONObject(i);
                                String brand = object.getString("brand");
                                String name = object.getString("name");
                                String gender = object.getString("gender");
                                String color = object.getString("color");
                                String price = object.getString("price");
                                String description = object.getString("description");
                                String img_url = object.getString("img_url");

                                Dress dress = new Dress(brand, name, gender, color, price, description, img_url);
                                dresses.add(dress);
                            }

                            adapter = new DressAdapter(DressActivity.this, dresses);
                            dressList.setAdapter(adapter);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("dsd", error.toString());

                    }
                });

        queue.add(jsonObjectRequest);

    }
}