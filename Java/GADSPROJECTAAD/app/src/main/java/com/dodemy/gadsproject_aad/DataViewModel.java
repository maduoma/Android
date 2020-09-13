package com.dodemy.gadsproject_aad;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dodemy.gadsproject_aad.datasource.APIClient;
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
        APIClient.getINSTANCE().getTopSkill().enqueue(new Callback<List<SkillIQ>>() {
            @Override
            public void onResponse(Call<List<SkillIQ>> call, Response<List<SkillIQ>> response) {
                if (response.isSuccessful())
                skillMutableData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillIQ>> call, Throwable t) {
                Log.d("getTopSill()", "onFailure: "+new Throwable(t));
            }
        });
    }

    public void getTopLearner() {
        APIClient.getINSTANCE().getTopLearners().enqueue(new Callback<List<TopLearner>>() {
            @Override
            public void onResponse(Call<List<TopLearner>> call, Response<List<TopLearner>> response) {
                if (response.isSuccessful())
                topLearnerMutableData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TopLearner>> call, Throwable t) {
                Log.d("getTopLearner()", "onFailure: "+ t.toString());

            }
        });
    }
}
