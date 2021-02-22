package com.dodemy.productswitharchitecturecomponents.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.dodemy.productswitharchitecturecomponents.repository.model.Product
import com.dodemy.productswitharchitecturecomponents.utilities.GET_ALL_PRODUCTS_QUERY

@Dao
interface ProductsDao {

    @Insert(onConflict = REPLACE)
    fun saveProducts(products: Array<Product>)

    @Query(GET_ALL_PRODUCTS_QUERY)
    fun getAllProducts(): LiveData<List<Product>>
}