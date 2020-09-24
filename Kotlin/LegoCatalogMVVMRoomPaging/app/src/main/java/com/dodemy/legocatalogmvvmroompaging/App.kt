package com.dodemy.legocatalogmvvmroompaging

import android.app.Activity
import android.app.Application
import com.dodemy.legocatalogmvvmroompaging.di.AppInjector
import com.dodemy.legocatalogmvvmroompaging.util.CrashReportingTree
import com.facebook.stetho.Stetho
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
//import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector{//, HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        //AndroidInjection.inject(this)
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())

        AppInjector.init(this)
    }
   //override
   override fun activityInjector() = dispatchingAndroidInjector
}