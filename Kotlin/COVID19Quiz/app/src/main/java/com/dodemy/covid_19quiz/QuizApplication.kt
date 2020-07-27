package com.dodemy.covid_19quiz

import android.app.Application
import com.dodemy.covid_19quiz.data.local.LocalRepository
import com.dodemy.covid_19quiz.data.remote.RemoteRepository
import com.dodemy.covid_19quiz.domain.use_cases.GetQuizLocal
import com.dodemy.covid_19quiz.domain.use_cases.GetQuizRemote
import com.dodemy.covid_19quiz.domain.use_cases.InsertQuiz
import com.dodemy.covid_19quiz.framework.Iterators
import com.dodemy.covid_19quiz.framework.LocalDataSource
import com.dodemy.covid_19quiz.framework.QuizViewModelFactory
import com.dodemy.covid_19quiz.framework.RemoteDataSource


class QuizApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val localRepository = LocalRepository(LocalDataSource(this))
        val remoteRepository = RemoteRepository(RemoteDataSource())

        QuizViewModelFactory.inject(
            this,
            Iterators(
                InsertQuiz(localRepository),
                GetQuizRemote(remoteRepository),
                GetQuizLocal(localRepository)
            )
        )
    }
}