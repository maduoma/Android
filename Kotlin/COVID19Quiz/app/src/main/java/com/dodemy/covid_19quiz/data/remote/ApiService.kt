package com.dodemy.covid_19quiz.data.remote

import com.dodemy.covid_19quiz.domain.entities.QuizData
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("questions")
    fun getQuiz(): Call<List<QuizData>>
}