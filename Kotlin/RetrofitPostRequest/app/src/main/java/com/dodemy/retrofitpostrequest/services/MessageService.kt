package com.dodemy.retrofitpostrequest.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface MessageService {

    @GET
    fun getMessages(@Url anotherUrl: String): Call<String>
}