package com.dodemy.room_bakingapp.data.db.entities;



import androidx.room.Embedded;

public class RecipeWithIngredientStep {
    @Embedded
    public RecipeResponse response;

//    @Relation(parentColumn = "id",
//            entityColumn = "responseId")
//    public List<Ingredient> ingredientList;
//
//    @Relation(parentColumn = "id",
//            entityColumn = "responseId")
//    public List<Step> stepList;
}
