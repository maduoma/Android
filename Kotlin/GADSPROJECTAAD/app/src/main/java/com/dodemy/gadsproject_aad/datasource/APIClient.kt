package com.dodemy.gadsproject_aad.datasource

import com.dodemy.gadsproject_aad.model.SkillIQ
import com.dodemy.gadsproject_aad.model.TopLearner
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class APIClient {
    private val apiInterface: APIInterface
    val topSkill: Call<List<SkillIQ?>?>?
        get() = apiInterface.topSkill
    val topLearners: Call<List<TopLearner?>?>?
        get() = apiInterface.topLearners

    companion object {
        private const val BASE_URL = "https://gadsapi.herokuapp.com/"

        //Singleton
        var INSTANCE: APIClient? = null
            get() {
                if (null == field) {
                    field = APIClient()
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
        apiInterface = retrofit.create(APIInterface::class.java)
    }
}