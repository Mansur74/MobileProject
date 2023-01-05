package com.example.mobileproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MotionEventCompat;

import android.os.Bundle;
import android.view.MotionEvent;
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
import com.example.mobileproject.adapters.SuitAdapter;
import com.example.mobileproject.models.Suit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SuitActivity extends AppCompatActivity {
    ListView suitList;
    List<Suit> suits;
    SuitAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suit);

        suitList = findViewById(R.id.suit_list);
        suits = new ArrayList<>();

        adapter = new SuitAdapter(SuitActivity.this, suits);
        suitList.setAdapter(adapter);

        toolbar = findViewById(R.id.toolbar);
        ImageButton backButton = toolbar.findViewById(R.id.back);
        TextView textView = toolbar.findViewById(R.id.name);
        textView.setText("Suits");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.jsonkeeper.com/b/PCI1";
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
                            for(int i=0; i <  response.getJSONArray("suits").length(); i++)
                            {
                               JSONObject object = response.getJSONArray("suits").getJSONObject(i);
                               String brand = object.getString("brand");
                               String name = object.getString("name");
                               String color = object.getString("color");
                               String price = object.getString("price");
                               String description = object.getString("description");
                               String img_url = object.getString("img_url");

                               Suit suit = new Suit(brand, name, color, price, description, img_url);
                               suits.add(suit);
                            }

                            adapter = new SuitAdapter(SuitActivity.this, suits);
                            suitList.setAdapter(adapter);

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