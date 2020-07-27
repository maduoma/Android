package com.dodemy.covid_19quiz.framework

import android.content.Context
import androidx.lifecycle.LiveData
import com.dodemy.covid_19quiz.data.local.QuizDao
import com.dodemy.covid_19quiz.data.local.QuizDatabase
import com.dodemy.covid_19quiz.domain.entities.BaseResponse


class LocalDataSource(context: Context) : QuizDao {
    private val quizDao = QuizDatabase.getDatabase(context).quizDao()

    override suspend fun insertQuiz(quizData: BaseResponse?) {
        quizDao.insertQuiz(quizData)
    }

    override fun getQuiz(): LiveData<BaseResponse> {
        return quizDao.getQuiz()
    }
}