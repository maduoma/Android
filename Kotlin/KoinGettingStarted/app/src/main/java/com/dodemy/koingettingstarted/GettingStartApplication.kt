package com.dodemy.koingettingstarted

import android.app.Application
import com.dodemy.koingettingstarted.di.networkModule
import com.dodemy.koingettingstarted.di.repositoryModule
import com.dodemy.koingettingstarted.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 *
 *
 * Usage:
 *
 * How to call:
 *
 * Useful parameter:
 *
 */

class GettingStartApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GettingStartApplication)
            modules(listOf(repositoryModule, networkModule, viewModelModule))
        }
    }
}