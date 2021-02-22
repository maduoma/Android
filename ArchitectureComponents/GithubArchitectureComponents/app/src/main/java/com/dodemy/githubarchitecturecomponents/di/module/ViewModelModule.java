package com.dodemy.githubarchitecturecomponents.di.module;



import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dodemy.githubarchitecturecomponents.di.key.ViewModelKey;
import com.dodemy.githubarchitecturecomponents.view_models.FactoryViewModel;
import com.dodemy.githubarchitecturecomponents.view_models.UserProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;



@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel.class)
    abstract ViewModel bindUserProfileViewModel(UserProfileViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
