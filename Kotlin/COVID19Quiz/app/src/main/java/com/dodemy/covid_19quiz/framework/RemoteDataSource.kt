package com.dodemy.covid_19quiz.framework

import com.dodemy.covid_19quiz.data.remote.ApiClient
import com.dodemy.covid_19quiz.data.remote.ApiService
import com.dodemy.covid_19quiz.domain.entities.QuizData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


class RemoteDataSource : ApiService {

    private val client = ApiClient.getClient()?.create(ApiService::class.java)

    override fun getQuiz(): Call<List<QuizData>> {
        return client!!.getQuiz()
    }
}