package com.dodemy.gadsleaderboard

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    private var retrofit: Retrofit? = null
    fun getClient(): APIInterface? {

        // change your base URL
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("http://mobileappdatabase.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        //Creating object for our interface
        return retrofit!!.create(APIInterface::class.java) // return the APIInterface object
    }
}