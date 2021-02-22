/*
 * Copyright (c) 2021. ~ Maduabughichi Achilefu.
 */

package com.dodemy.productswitharchitecturecomponents

import com.dodemy.productswitharchitecturecomponents.repository.ProductsRepository
import com.dodemy.productswitharchitecturecomponents.repository.local.LocalRepository
import com.dodemy.productswitharchitecturecomponents.repository.local.ProductsDatabase
import com.dodemy.productswitharchitecturecomponents.repository.remote.RemoteRepository
import com.dodemy.productswitharchitecturecomponents.repository.remote.WebService
import com.dodemy.productswitharchitecturecomponents.utilities.API_BASE_URL
import com.dodemy.productswitharchitecturecomponents.utilities.ConnectivityAgent
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors


val repositoryModule = applicationContext {
    bean { ProductsRepository(get(), get(), get(), get()) }

    bean { ConnectivityAgent(androidApplication()) }
    bean { Executors.newSingleThreadExecutor() as Executor }

    bean { LocalRepository(get()) }
    bean { ProductsDatabase.instance(androidApplication()).productsDao() }

    bean { RemoteRepository(get()) }
    bean { buildRetrofitInstance().create(WebService::class.java) as WebService }
}

private fun buildRetrofitInstance() = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
