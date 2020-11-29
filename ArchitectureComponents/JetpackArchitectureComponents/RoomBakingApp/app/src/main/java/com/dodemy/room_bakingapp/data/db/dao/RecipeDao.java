package com.dodemy.room_bakingapp.data.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.dodemy.room_bakingapp.data.db.entities.RecipeWithIngredientStep;

import java.util.List;


@Dao
public interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipe")
    LiveData<List<RecipeWithIngredientStep>> getRecipeList();

//    @Query("SELECT COUNT(id) FROM recipe WHERE id = :recipeId")
//    LiveData<Integer> isFavourite(int recipeId);

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertRecipeList(List<RecipeWithIngredientStep> responses);

//    @Query("DELETE FROM recipe WHERE id = :deleteId")
//    void deleteRecipe(int deleteId);

//    @Query("SELECT * FROM recipe WHERE id= :rowId")
//    LiveData<RecipeResponse> getRecipeDetails(int rowId);

}
