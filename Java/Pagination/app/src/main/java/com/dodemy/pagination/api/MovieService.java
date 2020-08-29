package com.dodemy.pagination.api;

import com.dodemy.pagination.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface MovieService {
    @GET("volley_array.json")
    Call<List<Movie>> getMovies();
}
