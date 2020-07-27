package com.dodemy.covid_19quiz.domain.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(
    @field:Json(name = "answer")
    val answer: String,
    @field:Json(name = "correct")
    val correct: Boolean,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "question_id")
    val question_id: Int
): Parcelable