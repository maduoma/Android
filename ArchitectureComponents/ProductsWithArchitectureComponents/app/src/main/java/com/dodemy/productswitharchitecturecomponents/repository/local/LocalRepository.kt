package com.dodemy.productswitharchitecturecomponents.repository.local

import com.dodemy.productswitharchitecturecomponents.repository.model.Product

class LocalRepository(private val productsDao: ProductsDao) {

    fun getProducts() = productsDao.getAllProducts()

    fun saveProducts(products: Array<Product>) = productsDao.saveProducts(products)
}