package com.example.movieapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapps.R;
import com.example.movieapps.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListMovies extends RecyclerView.Adapter<AdapterListMovies.ViewHolder> {

    Context context;
    List<Movies> moviesList;

    public AdapterListMovies(Context context, List<Movies> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_movies_layout, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String thumbnailUrl = moviesList.get(position).getPosterPath();
        Picasso.with(context).load("https://themoviedb.org/t/p/w500/" + thumbnailUrl).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.photos);

        String id = moviesList.get(position).getId().toString();
        holder.txtTitle.setText(moviesList.get(position).getTitle());
        holder.txtReleaseDate.setText(moviesList.get(position).getReleaseDate());
    }


    @Override
    public int getItemCount() {
        return moviesList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photos;
        TextView txtTitle, txtReleaseDate;

        public ViewHolder(View itemView) {
            super(itemView);

            photos = itemView.findViewById(R.id.icon);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtReleaseDate = itemView.findViewById(R.id.txtReleaseDate);
        }
    }

}
