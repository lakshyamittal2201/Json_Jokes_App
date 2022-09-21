package com.example.volleytestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

//    RequestQueue mRequestQueue;
//    RequestQueue mRequestQueueArray;

    RequestQueue mSingleRequestQueue;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mRequestQueue = Volley.newRequestQueue(this);
//        mRequestQueueArray = Volley.newRequestQueue(this);

        mSingleRequestQueue = VolleySingleton.getInstance().getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.chucknorris.io/jokes/random", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("RESPONSE VALID DATA", response + "");
                textView = findViewById(R.id.textBox);

                String setup = null;
                try {
                    setup = response.getString("value");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textView.setText(setup + "");

//                try {
//                    int jokeId = response.getInt("id");
//
//
//                    Log.i("Joke", response + "");
//
//
//
//
//                } catch (JSONException e) {
//                    Toast.makeText(getApplicationContext(), "Your Internet Connection is gone, Please try again!!", Toast.LENGTH_SHORT).show();
//
//
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE ERROR", error.getMessage());
            }
        });

        mSingleRequestQueue.add(jsonObjectRequest);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("ARRAY RESPONSE", response + "");

/*
                for (int index = 0; index < response.length(); index++) {

                    try {

                        JSONObject jokeJsonObject = response.getJSONObject(index);

                        Log.i("Joke", jokeJsonObject.getString("value"));

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                }*/



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ARRAY ERROR", error.getMessage());

            }
        });

        mSingleRequestQueue.add(jsonArrayRequest);

    }
}