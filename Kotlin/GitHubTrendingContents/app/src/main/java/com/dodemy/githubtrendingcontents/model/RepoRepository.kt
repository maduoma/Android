package com.dodemy.githubtrendingcontents.model



import com.dodemy.githubtrendingcontents.model.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {

    // GET repo list
    fun getRepoList(onResult: (isSuccess: Boolean, response: GitResponse?) -> Unit) {

        ApiClient.instance.getRepo().enqueue(object : Callback<GitResponse> {
            override fun onResponse(call: Call<GitResponse>?, response: Response<GitResponse>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<GitResponse>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }

    companion object {
        private var INSTANCE: RepoRepository? = null
        fun getInstance() = INSTANCE
                ?: RepoRepository().also {
                    INSTANCE = it
                }
    }
}