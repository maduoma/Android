package com.dodemy.kotlinsimplicity.dao

import com.dodemy.kotlinsimplicity.dto.FoodType
import retrofit2.http.GET

interface IFoodDAO {

    @GET("/discospiff/PlantPlacesKotlin/master/foodtypes.json")
    suspend fun getFoodTypes(): ArrayList<FoodType>
}