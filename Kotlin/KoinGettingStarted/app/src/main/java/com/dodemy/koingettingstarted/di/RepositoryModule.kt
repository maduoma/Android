package com.dodemy.koingettingstarted.di

import com.dodemy.koingettingstarted.data.repository.ImagesRepo
import com.dodemy.koingettingstarted.utils.LocationHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


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

val repositoryModule = module {
    single { ImagesRepo(androidContext(), imagesApi = get()) }

    single { LocationHandler() }
}