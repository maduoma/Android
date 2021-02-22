package com.dodemy.githubarchitecturecomponents.di.module;

import com.dodemy.githubarchitecturecomponents.fragments.UserProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;



@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract UserProfileFragment contributeUserProfileFragment();
}
