package com.dodemy.covid_19quiz.data.local

import com.dodemy.covid_19quiz.domain.entities.BaseResponse


class LocalRepository(private val dao: QuizDao) {
    suspend fun insertQuiz(quizData: BaseResponse?) = dao.insertQuiz(quizData)

    fun getQuiz() = dao.getQuiz()
}