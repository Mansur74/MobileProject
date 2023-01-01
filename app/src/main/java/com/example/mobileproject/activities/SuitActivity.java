package com.example.mobileproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suit);

        suitList = findViewById(R.id.suit_list);
        suits = new ArrayList<>();

        adapter = new SuitAdapter(SuitActivity.this, suits);
        suitList.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.jsonkeeper.com/b/OYSY";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

                        try {
                            for(int i=0; i <  response.getJSONArray("suit").length(); i++)
                            {
                               JSONObject object = response.getJSONArray("suit").getJSONObject(i);
                               String brand = object.getString("brand");
                               String gender = object.getString("gender");
                               String color = object.getString("color");
                               String date = object.getString("date");

                               Suit suit = new Suit(brand, "bos",gender, color, date);
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

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_UP) :
                Toast.makeText(getApplicationContext(), "asfsffa", Toast.LENGTH_LONG).show();
                return true;

            default :
                return super.onTouchEvent(event);
        }
    }


}