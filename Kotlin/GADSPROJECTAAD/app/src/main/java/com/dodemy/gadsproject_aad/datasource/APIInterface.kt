package com.dodemy.gadsproject_aad.datasource

import com.dodemy.gadsproject_aad.model.SkillIQ
import com.dodemy.gadsproject_aad.model.TopLearner
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @get:GET("api/skilliq")
    val topSkill: Call<List<SkillIQ?>?>?

    @get:GET("api/hours")
    val topLearners: Call<List<TopLearner?>?>?
}