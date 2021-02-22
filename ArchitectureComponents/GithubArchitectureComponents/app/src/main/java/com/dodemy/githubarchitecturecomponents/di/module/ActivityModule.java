package com.dodemy.githubarchitecturecomponents.di.module;

import com.dodemy.githubarchitecturecomponents.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
