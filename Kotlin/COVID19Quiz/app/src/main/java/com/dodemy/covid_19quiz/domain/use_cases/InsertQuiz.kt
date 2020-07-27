package com.dodemy.covid_19quiz.domain.use_cases

import com.dodemy.covid_19quiz.data.local.LocalRepository
import com.dodemy.covid_19quiz.domain.entities.BaseResponse
import com.dodemy.covid_19quiz.domain.entities.QuizData



class InsertQuiz(private val localRepository: LocalRepository) {
    suspend operator fun invoke(quizData: BaseResponse?) = localRepository.insertQuiz(quizData)
}