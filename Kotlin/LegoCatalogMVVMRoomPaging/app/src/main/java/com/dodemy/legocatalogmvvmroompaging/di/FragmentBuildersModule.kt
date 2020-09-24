package com.dodemy.legocatalogmvvmroompaging.di


import com.dodemy.legocatalogmvvmroompaging.legoset.ui.LegoSetFragment
import com.dodemy.legocatalogmvvmroompaging.legoset.ui.LegoSetsFragment
import com.dodemy.legocatalogmvvmroompaging.legotheme.ui.LegoThemeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeThemeFragment(): LegoThemeFragment

    @ContributesAndroidInjector
    abstract fun contributeLegoSetsFragment(): LegoSetsFragment

    @ContributesAndroidInjector
    abstract fun contributeLegoSetFragment(): LegoSetFragment
}
