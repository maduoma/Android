package com.dodemy.covid_19quiz.data.remote


class RemoteRepository(private val service: ApiService) {
    fun getQuiz() = service.getQuiz()
}