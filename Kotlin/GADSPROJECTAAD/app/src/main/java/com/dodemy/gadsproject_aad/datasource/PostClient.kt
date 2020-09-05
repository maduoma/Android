package com.dodemy.gadsproject_aad.datasource

import com.dodemy.gadsproject_aad.model.SkillIQ
import com.dodemy.gadsproject_aad.model.TopLearner
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PostClient {
    private val postInterface: PostInterface
    val topSkill: Call<List<SkillIQ?>?>?
        get() = postInterface.topSkill
    val topLearners: Call<List<TopLearner?>?>?
        get() = postInterface.topLearners

    companion object {
        private const val BASE_URL = "https://gadsapi.herokuapp.com/"

        //Singleton
        var INSTANCE: PostClient? = null
            get() {
                if (null == field) {
                    field = PostClient()
                }
                return field
            }
            private set
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        postInterface = retrofit.create(PostInterface::class.java)
    }
}