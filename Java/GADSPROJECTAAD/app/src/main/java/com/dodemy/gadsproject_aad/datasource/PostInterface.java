package com.dodemy.gadsproject_aad.datasource;

import com.dodemy.gadsproject_aad.model.SkillIQ;
import com.dodemy.gadsproject_aad.model.TopLearner;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("api/skilliq")
    Call<List<SkillIQ>> getTopSkill();

    @GET("api/hours")
    Call<List<TopLearner>> getTopLearners();

}
