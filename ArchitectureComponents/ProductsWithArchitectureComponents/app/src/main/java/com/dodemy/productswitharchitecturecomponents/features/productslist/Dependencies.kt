package com.dodemy.productswitharchitecturecomponents.features.productslist

import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext


val productsListModule = applicationContext {
    viewModel { ProductsViewModel(get()) }
}