package com.dodemy.gadsleaderboardaad.network

import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SubmissionService {

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun makeSubmissionAsync(
        @Field("entry.1824927963") emailAddress: String,
        @Field("entry.1877115667") name: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") linkToProject: String
    ): Deferred<Unit>
}