package com.dodemy.covid_19quiz.domain.use_cases

import com.dodemy.covid_19quiz.data.remote.RemoteRepository



class GetQuizRemote(private val remoteRepository: RemoteRepository) {
    operator fun invoke() = remoteRepository.getQuiz()
}