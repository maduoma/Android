package com.dodemy.githubarchitecturecomponents.api;

import com.dodemy.githubarchitecturecomponents.database.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface UserWebservice {
    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String userId);
}
