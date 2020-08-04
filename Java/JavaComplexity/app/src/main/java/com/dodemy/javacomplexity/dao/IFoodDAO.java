package com.dodemy.javacomplexity.dao;

import com.dodemy.javacomplexity.dto.FoodType;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;

public interface IFoodDAO {
    @GET("/discospiff/PlantPlacesKotlin/master/foodtypes.json")
    Call<List<FoodType>> getFoodTypes();
}
