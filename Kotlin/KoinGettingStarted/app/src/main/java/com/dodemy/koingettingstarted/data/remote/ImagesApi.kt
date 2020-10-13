package com.dodemy.koingettingstarted.data.remote

import android.telecom.Call
import com.dodemy.koingettingstarted.data.model.ImagesResponseModel
import retrofit2.Call
import retrofit2.http.GET

/**
 *
 *
 * Usage: a interface to define end points
 *
 * How to call: just add in appInjector and repositoryImpl that you wanna use
 *
 */
interface ImagesApi {
    @GET("images/search?query=l")
    fun getImages() : Call<ImagesResponseModel>
}