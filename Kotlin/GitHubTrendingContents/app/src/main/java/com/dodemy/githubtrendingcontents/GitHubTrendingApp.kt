package com.dodemy.githubtrendingcontents

import android.app.Application

class GitHubTrendingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: GitHubTrendingApp
    }
}