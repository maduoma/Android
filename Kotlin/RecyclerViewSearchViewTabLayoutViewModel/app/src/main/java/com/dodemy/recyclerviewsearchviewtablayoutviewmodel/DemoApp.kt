package com.dodemy.recyclerviewsearchviewtablayoutviewmodel

import android.app.Application

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DemoApp)
            modules(searchViewActivityModule)
        }

    }

}