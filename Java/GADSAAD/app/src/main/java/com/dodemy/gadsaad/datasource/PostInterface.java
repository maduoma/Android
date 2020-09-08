package com.dodemy.gadsaad.datasource;

import com.dodemy.gadsaad.model.SkillIQ;
import com.dodemy.gadsaad.model.TopLearner;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("api/skilliq")
    Call<List<SkillIQ>> getTopSkill();

    @GET("api/hours")
    Call<List<TopLearner>> getTopLearners();

}
