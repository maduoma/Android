package com.dodemy.room_bakingapp.data.prefs;


public interface PrefHelper {
    boolean checkDbExistOrNot();

    void setDbExist(boolean dbExist);
    String getCurrentRecipeIngredient();
    void setCurrentRecipeIngredient(String text);
}
