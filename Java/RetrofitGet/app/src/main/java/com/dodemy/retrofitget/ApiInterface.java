package com.dodemy.retrofitget;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;


public interface ApiInterface {

    // API's endpoints
    @GET("/retrofit/getuser.php")
    public void getUsersList(Callback<List<UserListResponse>> callback);

// UserListResponse is POJO class to get the data from API, In above method we use List<UserListResponse> because the data in our API is starting from JSONArray

}
