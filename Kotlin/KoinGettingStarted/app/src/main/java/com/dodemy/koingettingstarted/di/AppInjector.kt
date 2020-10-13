package com.dodemy.koingettingstarted.di

import com.dodemy.koingettingstarted.data.remote.ImagesApi

import org.koin.dsl.module
import retrofit2.Retrofit


/**
 *
 *
 * Usage: this methods handle DI
 *
 * How to call: just startActivityForResult koin in application class
 *
 */

private val retrofit: Retrofit = createNetworkClient()


private val IMAGES_API: ImagesApi = retrofit.create(ImagesApi::class.java)

val networkModule = module {
    single { IMAGES_API }
}