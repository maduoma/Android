package com.dodemy.covid_19quiz.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize



@Parcelize
@Entity(tableName = "quiz_table")
data class BaseResponse(
    @PrimaryKey val id: Long = 0,
    val questions: List<QuizData>?
) : Parcelable