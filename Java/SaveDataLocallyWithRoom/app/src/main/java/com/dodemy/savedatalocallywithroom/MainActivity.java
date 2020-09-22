package com.dodemy.savedatalocallywithroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    In the latest update, the documentation in Android recommends to use java.util.concurrent instead as AsyncTask was deprecated in API level R.
    Remember that we have actually declared an ExecutorService called databaseWriteExecutor last time. We can use it as replacement for AsyncTask.
    Simply call the execute function and pass a Runnable object, eg:
    AppDatabase.databaseWriteExecutor.execute(new Runnable() {
    @Override
    public void run() {
        List<User> users = AppDatabase.getDatabase(getApplicationContext()).userDao().getAll();
        for(User user : users) {
            Log.d("User", u.getDebugString());
        }
    }
});
     */
    //Simply place all your codes that update the UI inside the run function and you are good to go.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

////The following example selects all data from the table and stores it inside a list of users:
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                List<User> users = AppDatabase.getDatabase(getApplicationContext()).userDao().getAll();
//                for (User user : users) {
//                    Log.d("User", user.getDebugString());
//                }
//            }
//        });
//
//
//        //Insert Data
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                Date date = new Date();
//                User user = new User(date.getTime(), "To N Togo");
//                AppDatabase.getDatabase(getApplicationContext()).userDao().insertAll(user);
//            }
//        });
//
//        //The following example selects all data from the table and stores it inside a list of users
//        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                List<User> users = AppDatabase.getDatabase(getApplicationContext()).userDao().getAll();
//                for (User user : users) {
//                    Log.d("User", user.getDebugString());
//                }
//            }
//        });
        insertData();
        listOfUsers();
    }

    //Insert Data
    private void insertData() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                User user = new User(date.getTime(), "To N Togo");
                AppDatabase.getDatabase(getApplicationContext()).userDao().insertAll(user);
            }
        });
    }

    ////The following example selects all data from the table and stores it inside a list of users
    private void listOfUsers() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<User> users = AppDatabase.getDatabase(getApplicationContext()).userDao().getAll();
                for (User user : users) {
                    Log.d("User", user.getDebugString());
                }
            }
        });
    }
}