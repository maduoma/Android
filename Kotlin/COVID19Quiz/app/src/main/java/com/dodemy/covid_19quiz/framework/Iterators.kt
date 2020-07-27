package com.dodemy.covid_19quiz.framework

import com.dodemy.covid_19quiz.domain.use_cases.GetQuizLocal
import com.dodemy.covid_19quiz.domain.use_cases.GetQuizRemote
import com.dodemy.covid_19quiz.domain.use_cases.InsertQuiz


data class Iterators(
    val insertQuiz: InsertQuiz,
    val getQuizRemote: GetQuizRemote,
    val getQuizLocal: GetQuizLocal
)