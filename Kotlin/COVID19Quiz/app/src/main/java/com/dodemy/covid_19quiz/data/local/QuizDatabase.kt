package com.dodemy.covid_19quiz.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dodemy.covid_19quiz.domain.entities.BaseResponse
import com.dodemy.covid_19quiz.util.QuizTypeConverters


@Database(entities = [BaseResponse::class], version = 1, exportSchema = false)
@TypeConverters(QuizTypeConverters::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java, "quiz_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}