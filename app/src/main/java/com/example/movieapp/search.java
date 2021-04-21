package com.example.movieapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {
    RecyclerView searchrecycle;
    trendmoviedata trendmoviedata;
    List<trendmoviedata> searchlist;
    EditText searchbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchrecycle=findViewById(R.id.searchrecycle);
        searchbox=findViewById(R.id.searchbox);

    }
    public void searchit(View view){
        searchlist = new ArrayList<>();
        String queary=searchbox.getText().toString();
        String url="https://api.themoviedb.org/3/search/movie?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&query="+queary+"&page=1&include_adult=false";

        StringRequest requestnp = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {



                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        // Log.i("name",jsonObject1.getString("title"));
                        String title = jsonObject1.getString("title");
                        String date = jsonObject1.getString("release_date");
                        String lang = jsonObject1.getString("original_language");
                        String poster = jsonObject1.getString("poster_path");
                        String overview = jsonObject1.getString("overview");
                        Integer count = jsonObject1.getInt("vote_count");
                        Integer rate = jsonObject1.getInt("vote_average");
                        Boolean adult = jsonObject1.getBoolean("adult");
                        Integer id = jsonObject1.getInt("id");

                        trendmoviedata = new trendmoviedata(title, date, lang, poster, overview, count, rate, id, adult);
                        searchlist.add(trendmoviedata);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(search.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueuenp = Volley.newRequestQueue(this);
        requestQueuenp.add(requestnp);
        try {
            trendadapter trendadapter = new trendadapter(search.this, searchlist);
            searchrecycle.setAdapter(trendadapter);
            searchrecycle.setLayoutManager(new GridLayoutManager(search.this, 2));
        } catch (Exception e) {
            Log.i("error", e.toString());
        }
    }
    }
