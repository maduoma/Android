package com.dodemy.room_bakingapp.data;


import androidx.lifecycle.LiveData;

import com.dodemy.room_bakingapp.data.db.LocalDbHelper;
import com.dodemy.room_bakingapp.data.db.entities.RecipeResponse;
import com.dodemy.room_bakingapp.data.db.entities.RecipeWithIngredientStep;
import com.dodemy.room_bakingapp.data.prefs.PrefHelper;
import com.dodemy.room_bakingapp.data.remote.NetworkDataSource;

import java.util.List;

import timber.log.Timber;


public class FoodRepository implements RepositoryHelper {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static FoodRepository sInstance;
    private final NetworkDataSource foodNetworkDataSource;
    private final PrefHelper preferenceHelper;
    private final LocalDbHelper dbHelper;

    private FoodRepository(NetworkDataSource networkSource,
                           PrefHelper preferenceHelper, LocalDbHelper dbHelper) {
        this.dbHelper = dbHelper;
        foodNetworkDataSource = networkSource;
        this.preferenceHelper = preferenceHelper;
    }

    public synchronized static FoodRepository getInstance(
            NetworkDataSource weatherNetworkDataSource,
            PrefHelper preferenceHelper, LocalDbHelper dbHelper) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new FoodRepository(weatherNetworkDataSource,
                        preferenceHelper, dbHelper);
            }
        }
        Timber.d("returning instance of repository");
        return sInstance;
    }

    // insert recipe list in sqlite db
//    @Override
//    public void insertRecipeList(List<RecipeWithIngredientStep> recipeResponses) {
//        dbHelper.insertRecipeList(recipeResponses);
//    }

    //Todo Not able to understand, insertion part here
    // get recipe list , check if db-exist or not. if exist send from sqlite or fetch data
    @Override
    public LiveData<List<RecipeResponse>> getRecipeList() {
        LiveData<List<RecipeResponse>> responseList = null;
//        if (!checkDbExistOrNot()) {
//
//
//            //insertRecipeList(responseList.getValue());
//            //setDbExist(true);
//
//        }
//        else {
//            //return dbHelper.getRecipeList();
//        }
        responseList =  foodNetworkDataSource.getRecipesFromNetwork();
        return responseList;
    }

    // check from preference if db already exist or not
    @Override
    public boolean checkDbExistOrNot() {
        return preferenceHelper.checkDbExistOrNot();
    }

    // set db now exist or deleted
    @Override
    public void setDbExist(boolean dbExist) {
        preferenceHelper.setDbExist(dbExist);
    }

    @Override
    public String getCurrentRecipeIngredient() {
        return preferenceHelper.getCurrentRecipeIngredient();
    }

    @Override
    public void setCurrentRecipeIngredient(String text) {
        preferenceHelper.setCurrentRecipeIngredient(text);
    }
}
