package com.dodemy.covid_19quiz.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuizData(
    @field:Json(name = "answers")
    val answers: List<Answer>,
    @field:Json(name = "question")
    val question: String
) : Parcelable