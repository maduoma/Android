package com.dodemy.gadsprojectwithretrofit;

import android.widget.ImageView;

import com.dodemy.gadsprojectwithretrofit.Models.LearningLeaders;
import com.dodemy.gadsprojectwithretrofit.Models.SkillIQLeaders;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utilities {
    public static class GadsApiUtility {
        public static final String GADS_API_BASE_URL = "https://gadsapi.herokuapp.com/api/";
        public static final String GOOGLE_FORM_API_BASE_URL = "https://docs.google.com/forms/d/e/";

        public static Retrofit mRetrofitGads = new Retrofit
                .Builder()
                .baseUrl(GADS_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        public static Retrofit mRetrofitGoogleForm = new Retrofit
                .Builder()
                .baseUrl(GOOGLE_FORM_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        public static HerokuAppAPI mLeadersAPI = mRetrofitGads.create(HerokuAppAPI.class);
        public static HerokuAppAPI mProjectSubmissionAPI = mRetrofitGoogleForm.create(HerokuAppAPI.class);

        public static Call<Void> submitProject(String fName, String lName, String email, String link) {
            return mProjectSubmissionAPI.submitForm(fName, lName, email, link);
        }

        public static Call<List<LearningLeaders>> fetchLearningLeaders() {
            return mLeadersAPI.getLearningLeader();
        }

        public static Call<List<SkillIQLeaders>> fetchSkillIQLeaders() {
            return mLeadersAPI.getSkillIQLeaders();
        }
    }

    public static class ImagesUtility {
        public static void loadImage(ImageView imageView, String imageUrl) {
            Picasso
                    .get()
                    .load(imageUrl)
                    .fit()
                    .placeholder(R.color.colorAccent)
                    .into(imageView)
            ;
        }
    }
}
