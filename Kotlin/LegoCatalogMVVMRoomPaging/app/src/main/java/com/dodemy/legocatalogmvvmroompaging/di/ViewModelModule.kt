package com.dodemy.legocatalogmvvmroompaging.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dodemy.legocatalogmvvmroompaging.legoset.ui.LegoSetViewModel
import com.dodemy.legocatalogmvvmroompaging.legoset.ui.LegoSetsViewModel
import com.dodemy.legocatalogmvvmroompaging.legotheme.ui.LegoThemeViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LegoThemeViewModel::class)
    abstract fun bindThemeViewModel(viewModel: LegoThemeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LegoSetsViewModel::class)
    abstract fun bindLegoSetsViewModel(viewModel: LegoSetsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LegoSetViewModel::class)
    abstract fun bindLegoSetViewModel(viewModel: LegoSetViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
