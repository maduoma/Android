package com.dodemy.moviemvvmarchitectecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.dodemy.moviemvvmarchitectecture.model.EntityMovieItem;
import com.dodemy.moviemvvmarchitectecture.model.EntityMovieOutputs;
import com.dodemy.moviemvvmarchitectecture.viewmodel.ViewModelMovieOutputs;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<EntityMovieOutputs> listOfMovies = new ArrayList<>();
    MovieAdapter mAdapter;
    RecyclerView mPoster;
    ViewModelMovieOutputs viewModelMovieOutputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPoster = findViewById(R.id.posters_view);

        viewModelMovieOutputs= ViewModelProviders.of(this).get(ViewModelMovieOutputs.class);
        viewModelMovieOutputs.init();
        viewModelMovieOutputs.getMoviesRepository().observe(this, movieResponse -> {
            List<EntityMovieItem> mItems = movieResponse.getResults();
            listOfMovies.addAll(mItems);
            mAdapter.notifyDataSetChanged();
        });
        setupRecyclerView();
    }
    private void setupRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new MovieAdapter(MainActivity.this, listOfMovies);
            mPoster.setLayoutManager(new LinearLayoutManager(this));
            mPoster.setAdapter(mAdapter);
            mPoster.setItemAnimator(new DefaultItemAnimator());
            mPoster.setNestedScrollingEnabled(true);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}