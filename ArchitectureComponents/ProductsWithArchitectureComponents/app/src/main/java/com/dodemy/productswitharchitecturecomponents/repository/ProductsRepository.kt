/*
 * Copyright (c) 2021 Maduabughichi Achilefu.
 */
package com.dodemy.productswitharchitecturecomponents.repository

import androidx.lifecycle.LiveData
import com.dodemy.productswitharchitecturecomponents.repository.local.LocalRepository
import com.dodemy.productswitharchitecturecomponents.repository.model.Product
import com.dodemy.productswitharchitecturecomponents.repository.remote.RemoteRepository
import com.dodemy.productswitharchitecturecomponents.utilities.ConnectivityAgent
import java.util.concurrent.Executor

class ProductsRepository(
    private val connectivityAgent: ConnectivityAgent,
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val executor: Executor
) {

    fun getProducts(): LiveData<List<Product>> {
        refreshProducts()
        return localRepository.getProducts()
    }

    fun refreshProducts() {
        if (!connectivityAgent.isDeviceConnectedToInternet())
            return

        executor.execute {
            val response = remoteRepository.getProducts().execute()
            response.body()?.toTypedArray()?.let {
                localRepository.saveProducts(it)
            }
        }
    }
}