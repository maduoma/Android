package com.dodemy.productswitharchitecturecomponents.features.productslist

import androidx.lifecycle.ViewModel
import com.dodemy.productswitharchitecturecomponents.repository.ProductsRepository

class ProductsViewModel(private val repository: ProductsRepository) : ViewModel() {

    private val products = repository.getProducts()

    fun getProducts() = products

    fun refreshProducts() = repository.refreshProducts()
}