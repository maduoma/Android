package com.dodemy.covid_19quiz.data.remote

import androidx.viewbinding.BuildConfig
//import com.dodemy.covid_19quiz.BuildConfig
import com.dodemy.covid_19quiz.util.AppConstants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiClient {
    fun getClient(): Retrofit? {
        var retrofit: Retrofit? = null
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit
    }
}