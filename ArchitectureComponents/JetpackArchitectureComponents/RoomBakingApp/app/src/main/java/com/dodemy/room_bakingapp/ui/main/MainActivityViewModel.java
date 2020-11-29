package com.dodemy.room_bakingapp.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dodemy.room_bakingapp.data.FoodRepository;
import com.dodemy.room_bakingapp.data.db.entities.RecipeResponse;
import com.dodemy.room_bakingapp.utils.InjectorUtil;

import java.util.List;


public class MainActivityViewModel extends AndroidViewModel {

    private FoodRepository repository;
    private LiveData<List<RecipeResponse>> recipeLiveData;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = InjectorUtil.provideRepository(application.getApplicationContext());
        recipeLiveData = repository.getRecipeList();
    }

    public LiveData<List<RecipeResponse>> getRecipeLiveData() {
        return recipeLiveData;
    }
}
