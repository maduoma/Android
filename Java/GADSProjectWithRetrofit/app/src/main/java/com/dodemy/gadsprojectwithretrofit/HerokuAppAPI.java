package com.dodemy.gadsprojectwithretrofit;

import com.dodemy.gadsprojectwithretrofit.Models.LearningLeaders;
import com.dodemy.gadsprojectwithretrofit.Models.SkillIQLeaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HerokuAppAPI {

    @GET("hours")
    Call<List<LearningLeaders>> getLearningLeader();

    @GET("skilliq")
    Call<List<SkillIQLeaders>> getSkillIQLeaders();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Void> submitForm(
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.284483984") String link
    );
}
