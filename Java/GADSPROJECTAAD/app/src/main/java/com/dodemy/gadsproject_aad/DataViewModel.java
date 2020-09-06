package com.dodemy.gadsproject_aad;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dodemy.gadsproject_aad.datasource.PostClient;
import com.dodemy.gadsproject_aad.model.SkillIQ;
import com.dodemy.gadsproject_aad.model.TopLearner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataViewModel extends ViewModel {

    public MutableLiveData<List<SkillIQ>> skillMutableData = new MutableLiveData<>();
    public MutableLiveData<List<TopLearner>> topLearnerMutableData = new MutableLiveData<>();

    public void getTopSkill() {
        PostClient.getINSTANCE().getTopSkill().enqueue(new Callback<List<SkillIQ>>() {
            @Override
            public void onResponse(Call<List<SkillIQ>> call, Response<List<SkillIQ>> response) {
                skillMutableData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillIQ>> call, Throwable t) {
            }
        });
    }

    public void getTopLearner() {
        PostClient.getINSTANCE().getTopLearners().enqueue(new Callback<List<TopLearner>>() {
            @Override
            public void onResponse(Call<List<TopLearner>> call, Response<List<TopLearner>> response) {
                topLearnerMutableData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TopLearner>> call, Throwable t) {

            }
        });
    }
}
