package com.dodemy.room_todo

import android.app.Application
import android.content.Context

class MyApp: Application()
{
    init
    {
        instance = this
    }

    companion object
    {
        private var instance: MyApp? = null

        fun applicationContext() : Context
        {
            return instance!!.applicationContext
        }
    }
}