package com.dodemy.legocatalogmvvmroompaging.di

import com.dodemy.legocatalogmvvmroompaging.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
