package com.dodemy.android.movieswithmvvm.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.android.movieswithmvvm.R;
import com.dodemy.android.movieswithmvvm.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> movieList;

    public MovieAdapter() {

    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        final Movie movie = movieList.get(position);

        holder.titleTxt.setText(movie.getName());
        holder.descTxt.setText(movie.getDescription());
    }


    @Override
    public int getItemCount() {
        return movieList != null ? movieList.size() : 0;
    }

    public void setMovies(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }


    public static class MovieHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        TextView descTxt;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.title_txt);
            descTxt = itemView.findViewById(R.id.desc_txt);
        }
    }
}
