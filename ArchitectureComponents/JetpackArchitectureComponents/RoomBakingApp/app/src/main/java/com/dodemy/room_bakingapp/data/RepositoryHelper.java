package com.dodemy.room_bakingapp.data;



import androidx.lifecycle.LiveData;

import com.dodemy.room_bakingapp.data.db.LocalDbHelper;
import com.dodemy.room_bakingapp.data.db.entities.RecipeResponse;
import com.dodemy.room_bakingapp.data.prefs.PrefHelper;

import java.util.List;


public interface RepositoryHelper extends PrefHelper, LocalDbHelper {

    LiveData<List<RecipeResponse>> getRecipeList();
}
