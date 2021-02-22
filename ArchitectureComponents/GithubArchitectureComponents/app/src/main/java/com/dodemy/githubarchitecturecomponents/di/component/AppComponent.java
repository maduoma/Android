package com.dodemy.githubarchitecturecomponents.di.component;

import android.app.Application;

import com.dodemy.githubarchitecturecomponents.App;
import com.dodemy.githubarchitecturecomponents.di.module.ActivityModule;
import com.dodemy.githubarchitecturecomponents.di.module.AppModule;
import com.dodemy.githubarchitecturecomponents.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;



@Singleton
@Component(modules={AndroidSupportInjectionModule.class, ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
