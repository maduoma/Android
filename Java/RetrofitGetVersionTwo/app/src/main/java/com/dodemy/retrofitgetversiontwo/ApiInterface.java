package com.dodemy.retrofitgetversiontwo;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/retrofit/getuser.php")
        // API's endpoints
    Call<List<UserListResponse>> getUsersList();

// UserListResponse is POJO class to get the data from API, we use List<UserListResponse> in callback because the data in our API is starting from JSONArray
}
