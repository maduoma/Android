package com.dodemy.moviemvvmarchitectecture.utils;

import com.ronkan.movietray.MovieApplication;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(MovieApplication.getClient())
            .build();

    public static ApiInterface getInterface() {
        return retrofit.create(ApiInterface.class);
    }
}