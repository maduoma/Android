package com.dodemy.room_bakingapp;

import android.app.Application;
import android.content.Context;

//import butterknife.BuildConfig;
import butterknife.BuildConfig;
import timber.log.Timber;

public class App extends Application {

    private static App mInstance;

    public static Context getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = (App) getApplicationContext();
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
    }
}
