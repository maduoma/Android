package com.dodemy.room_bakingapp.data.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dodemy.room_bakingapp.data.db.dao.RecipeDao;
import com.dodemy.room_bakingapp.data.db.entities.Ingredient;
import com.dodemy.room_bakingapp.data.db.entities.RecipeResponse;
import com.dodemy.room_bakingapp.data.db.entities.Step;


@Database(entities = {RecipeResponse.class, Ingredient.class, Step.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "RecipeDb";
    private static volatile AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, AppDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }
}