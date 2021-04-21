package com.example.movieapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class trendadapter extends RecyclerView.Adapter<trendadapter.MyViewHolder> {
    private Context context;
    private List<trendmoviedata> trendlist;


    public trendadapter(Context context, List<trendmoviedata> trendlist){
        this.context=context;
        this.trendlist=trendlist;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(context);
        View view=mInflater.inflate(R.layout.moviecard,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull trendadapter.MyViewHolder holder, final int position) {
        try {
            String url = "https://image.tmdb.org/t/p/w500" + trendlist.get(position).getPoster();
            Glide.with(context).load(url)        .transform(new RoundedCornersTransformation(30, 0))

                    .into(holder.imgThumbnail);
            holder.moviename.setText(trendlist.get(position).getTitle());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, trendlist.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,moviedetails.class);
                    intent.putExtra("title",trendlist.get(position).getTitle());
                    intent.putExtra("overview",trendlist.get(position).getOverview());
                    intent.putExtra("lang",trendlist.get(position).getLang());
                    intent.putExtra("date",trendlist.get(position).getDate());
                    intent.putExtra("poster",trendlist.get(position).getPoster());
                    intent.putExtra("rate",trendlist.get(position).getRate());
                    intent.putExtra("adult",trendlist.get(position).getAdult());
                    intent.putExtra("id",trendlist.get(position).getId());
                    context.startActivity(intent);


                }
            });

        }

        catch (Exception e){
            Log.i("error",e.toString());
        }
        // holder.imgThumbnail.setImageResource(R.drawable.ic_banner_foreground);

    }

    @Override
    public int getItemCount() {
        return 20;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgThumbnail;
        TextView moviename;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);

            imgThumbnail=itemView.findViewById(R.id.nowplayingimageid);
            moviename=itemView.findViewById(R.id.nowplayingnameid);
            cardView=itemView.findViewById(R.id.nowplayingmoviecard);


        }
    }
}



