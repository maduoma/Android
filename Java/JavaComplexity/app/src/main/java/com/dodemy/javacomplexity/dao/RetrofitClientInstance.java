package com.dodemy.javacomplexity.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private  static Retrofit retrofit;
    private static final String BASE_URL = "https://raw.githubusercontent.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            // create a retrofit instance, only if it has not been created yet.
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}