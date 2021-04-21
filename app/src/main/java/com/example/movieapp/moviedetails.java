package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class moviedetails extends AppCompatActivity {
    TextView title,date,overview,age,rate,lang;
    ImageView img,imgbg;
    CardView cardView;
    RecyclerView similarrecycler;
    trendmoviedata trendmoviedata;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);
        List<trendmoviedata> similarlist = new ArrayList<>();


        title=findViewById(R.id.moviename);
        date=findViewById(R.id.moviedate);
        overview=findViewById(R.id.movieover);
        age=findViewById(R.id.movieage);
        rate=findViewById(R.id.movierate);
        lang=findViewById(R.id.movielang);
        img=findViewById(R.id.movieimg);
        imgbg=findViewById(R.id.movieimgbg);
        cardView=findViewById(R.id.card);
        similarrecycler=findViewById(R.id.similarrecycle);


        Intent intent = getIntent();
        String title1=intent.getExtras().getString("title");
        String date1=intent.getExtras().getString("date");
        String overview1 = intent.getExtras().getString("overview");
        String poster1=intent.getExtras().getString("poster");
        String lang1=intent.getExtras().getString("lang");
        Integer rate1=intent.getExtras().getInt("rate");
        boolean adult1=intent.getExtras().getBoolean("adult");
        Integer id=intent.getExtras().getInt("id");

        title.setText(title1);
        date.setText(date1);
        overview.setText(overview1);
        rate.setText(rate1.toString());
        lang.setText(lang1);
        if(adult1){
            age.setText("18+");
        }
        else {
            age.setText("13+");
        }
        String url = "https://image.tmdb.org/t/p/w500"+poster1;
        Glide.with(this).load(url).into(img);
        Glide.with(this).load(url)        .transform(new BlurTransformation())  .centerCrop()
        .into(imgbg);


       String url1=" https://api.themoviedb.org/3/movie/"+id.toString()+"/similar?api_key=d3bf2aee718b2374edaa0b9a3b477cf2&language=en-US&page=1";
       fetchdata(url1,similarlist,similarrecycler);


    }
    public void fetchdata(String url, final List list, RecyclerView recycle) {
        //now playing
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
                        list.add(trendmoviedata);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(moviedetails.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueuenp = Volley.newRequestQueue(this);
        requestQueuenp.add(requestnp);
        try {
            trendadapter trendadapter = new trendadapter(moviedetails.this, list);
            recycle.setAdapter(trendadapter);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(moviedetails.this, LinearLayoutManager.HORIZONTAL, false);
            recycle.setLayoutManager(horizontalLayoutManagaer);
        } catch (Exception e) {
            Log.i("error", e.toString());
        }
    }
}
