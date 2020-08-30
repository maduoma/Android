package com.dodemy.retrofitget;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static ApiInterface getClient() {
        // change your base URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mobileappdatabase.in/") //Set the Root URL
                .addConverterFactory(GsonConverterFactory.create())
                .build(); //Finally building the retrofit
        //Creating object for our interface
        ApiInterface api = retrofit.create(ApiInterface.class);
        return api; // return the APIInterface object
    }
}