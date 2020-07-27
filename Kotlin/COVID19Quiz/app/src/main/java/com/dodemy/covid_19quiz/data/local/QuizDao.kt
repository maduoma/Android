package com.dodemy.covid_19quiz.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dodemy.covid_19quiz.domain.entities.BaseResponse
import com.dodemy.covid_19quiz.domain.entities.QuizData

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quizData: BaseResponse?)

    @Query("SELECT * FROM quiz_table")
    fun getQuiz(): LiveData<BaseResponse>
}