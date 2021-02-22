package com.dodemy.productswitharchitecturecomponents.repository.remote

class RemoteRepository(private val webService: WebService) {

    fun getProducts() = webService.getProducts()
}