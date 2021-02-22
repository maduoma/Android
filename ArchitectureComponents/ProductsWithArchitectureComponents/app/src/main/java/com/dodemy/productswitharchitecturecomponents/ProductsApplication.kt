/*
 * Copyright (c) 2021 Maduabughichi Achilefu.
 */

package com.dodemy.productswitharchitecturecomponents

import android.app.Application
import com.dodemy.productswitharchitecturecomponents.features.productslist.productsListModule
import org.koin.android.ext.android.startKoin

class ProductsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(repositoryModule, productsListModule))
    }
}