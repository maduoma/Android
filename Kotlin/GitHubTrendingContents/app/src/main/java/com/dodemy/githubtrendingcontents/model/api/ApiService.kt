package com.dodemy.githubtrendingcontents.model.api

import com.dodemy.githubtrendingcontents.model.GitResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    fun getRepo(
        @Query("q") search: String = "trending",
        @Query("sort") sort: String = "stars"):
            Call<GitResponse>
}