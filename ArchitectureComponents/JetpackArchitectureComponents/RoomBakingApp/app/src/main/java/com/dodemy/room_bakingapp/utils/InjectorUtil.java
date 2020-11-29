package com.dodemy.room_bakingapp.utils;

import android.content.Context;

import com.dodemy.room_bakingapp.data.FoodRepository;
import com.dodemy.room_bakingapp.data.db.AppDatabase;
import com.dodemy.room_bakingapp.data.db.LocalDataSource;
import com.dodemy.room_bakingapp.data.db.LocalDbHelper;
import com.dodemy.room_bakingapp.data.prefs.AppPrefHelper;
import com.dodemy.room_bakingapp.data.remote.NetworkDataSource;

import timber.log.Timber;


public class InjectorUtil {
    public static FoodRepository provideRepository(Context context) {
        // local db
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        LocalDbHelper dbHelper = LocalDataSource.getInstance(database.recipeDao(), executors);
        // remote
        NetworkDataSource networkDataSource =
                new NetworkDataSource();
        // pref
        AppPrefHelper preferenceHelper = new AppPrefHelper(context, "movie-pref");
        Timber.d("providing repository");

        return FoodRepository.getInstance(networkDataSource, preferenceHelper, dbHelper);
    }

}
