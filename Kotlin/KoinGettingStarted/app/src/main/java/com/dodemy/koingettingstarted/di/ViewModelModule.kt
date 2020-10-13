package com.dodemy.koingettingstarted.di

import com.dodemy.koingettingstarted.view.main.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
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

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
}
