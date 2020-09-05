package com.dodemy.gadsleaderboardaad.network

import com.dodemy.gadsleaderboardaad.model.Hours
import com.dodemy.gadsleaderboardaad.model.Skills
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("api/hours")
    fun getHoursAsync(): Deferred<List<Hours>>

    @GET("api/skilliq")
    fun getSkillsAsync(): Deferred<List<Skills>>
}