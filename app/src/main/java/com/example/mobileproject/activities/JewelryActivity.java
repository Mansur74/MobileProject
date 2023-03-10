package com.example.mobileproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
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
import com.example.mobileproject.adapters.BouquetAdapter;
import com.example.mobileproject.adapters.JewelryAdapter;
import com.example.mobileproject.models.Bouquet;
import com.example.mobileproject.models.Jewelry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JewelryActivity extends AppCompatActivity {

    ListView jewelryList;
    List<Jewelry> jewelries;
    JewelryAdapter adapter;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewelry);

        jewelryList = findViewById(R.id.jewelry_list);
        jewelries = new ArrayList<>();

        adapter = new JewelryAdapter(JewelryActivity.this, jewelries);
        jewelryList.setAdapter(adapter);

        toolbar = findViewById(R.id.toolbar);
        ImageButton backButton = toolbar.findViewById(R.id.back);
        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("Jewelries");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.jsonkeeper.com/b/XYXO";
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
                            for(int i=0; i <  response.getJSONArray("jewelries").length(); i++)
                            {
                                JSONObject object = response.getJSONArray("jewelries").getJSONObject(i);
                                String name = object.getString("name");
                                String type = object.getString("type");
                                String description = object.getString("description");
                                String price = object.getString("price");
                                String img_url = object.getString("img_url");

                                Jewelry jewelry = new Jewelry(name, type, description, price, img_url);
                                jewelries.add(jewelry);
                            }

                            adapter = new JewelryAdapter(JewelryActivity.this, jewelries);
                            jewelryList.setAdapter(adapter);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        queue.add(jsonObjectRequest);
    }
}