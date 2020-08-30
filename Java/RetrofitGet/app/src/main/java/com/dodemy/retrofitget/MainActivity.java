package com.dodemy.retrofitget;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<UserListResponse> userListResponseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        getUserListData(); // call a method in which we have implement our GET type web API
    }

    private void getUserListData() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // getUsersList() is a method in API Interface class, in this method we define our API sub url
        Api.getClient().getUsersList(new Callback<List<UserListResponse>>() {
            @Override
            public void onResponse(Call<List<UserListResponse>> call, Response<List<UserListResponse>> response) {
                // in this method we will get the response from API
                progressDialog.dismiss(); //dismiss progress dialog
                userListResponseData = (List<UserListResponse>) call;
                setDataInRecyclerView(); // call this method to set the data in adapter

            }

            @Override
            public void onFailure(Call<List<UserListResponse>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss(); //dismiss progress dialog
            }

//            @Override
//            public void success(List<UserListResponse> userListResponses, Response response) {
//                // in this method we will get the response from API
//                progressDialog.dismiss(); //dismiss progress dialog
//                userListResponseData = userListResponses;
//                setDataInRecyclerView(); // call this method to set the data in adapter
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                // if error occurs in network transaction then we can get the error in this method.
//                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//                progressDialog.dismiss(); //dismiss progress dialog
//
//            }
        });
    }

    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this, userListResponseData);
        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView
    }
}
