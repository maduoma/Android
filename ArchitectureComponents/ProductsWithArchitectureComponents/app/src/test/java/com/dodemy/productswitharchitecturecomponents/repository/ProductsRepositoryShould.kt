/*
 * Copyright (c) 2021 Maduabughichi Achilefu.
 */

package com.dodemy.productswitharchitecturecomponents.repository

import com.dodemy.productswitharchitecturecomponents.repository.local.LocalRepository
import com.dodemy.productswitharchitecturecomponents.repository.remote.RemoteRepository
import com.dodemy.productswitharchitecturecomponents.utilities.ConnectivityAgent
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*
import java.util.concurrent.Executor


class ProductsRepositoryShould {

    private val connectivityAgent = mock(ConnectivityAgent::class.java)
    private val localRepository = mock(LocalRepository::class.java)
    private val remoteRepository = mock(RemoteRepository::class.java)
    private val executor = mock(Executor::class.java)

    private val productsRepository = ProductsRepository(
            connectivityAgent,
            localRepository,
            remoteRepository,
            executor
    )

    @Test
    fun returnLocalRepositoryProducts_whenInternetIsUnavailable() {
        given(connectivityAgent.isDeviceConnectedToInternet()).willReturn(false)

        productsRepository.getProducts()

        verify(localRepository).getProducts()
        verify(remoteRepository, never()).getProducts()
    }
}