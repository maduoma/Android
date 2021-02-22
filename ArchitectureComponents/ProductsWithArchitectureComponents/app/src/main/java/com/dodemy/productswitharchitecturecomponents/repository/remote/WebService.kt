/*
 * Copyright (c) 2021 Maduabughichi Achilefu.
 */


package com.dodemy.productswitharchitecturecomponents.repository.remote

import com.dodemy.productswitharchitecturecomponents.repository.model.Product
import com.dodemy.productswitharchitecturecomponents.utilities.PATH_ALL_PRODUCTS
import retrofit2.Call
import retrofit2.http.GET


interface WebService {

    @GET(PATH_ALL_PRODUCTS)
    fun getProducts(): Call<List<Product>>
}