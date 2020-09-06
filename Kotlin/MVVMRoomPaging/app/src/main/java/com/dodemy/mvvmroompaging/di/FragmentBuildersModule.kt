package com.dodemy.mvvmroompaging.di


import com.dodemy.mvvmroompaging.legoset.ui.LegoSetFragment
import com.dodemy.mvvmroompaging.legoset.ui.LegoSetsFragment
import com.dodemy.mvvmroompaging.legotheme.ui.LegoThemeFragment
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
