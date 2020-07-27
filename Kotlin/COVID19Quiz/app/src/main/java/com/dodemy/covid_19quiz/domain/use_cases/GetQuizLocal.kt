package com.dodemy.covid_19quiz.domain.use_cases

import com.dodemy.covid_19quiz.data.local.LocalRepository



class GetQuizLocal(private val localRepository: LocalRepository) {
    operator fun invoke() = localRepository.getQuiz()
}