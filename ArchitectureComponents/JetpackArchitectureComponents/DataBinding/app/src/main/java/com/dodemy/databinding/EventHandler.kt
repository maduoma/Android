package com.dodemy.databinding

import android.content.Context
import android.widget.Toast

open class EventHandler(context: Context) {
    private val myContext: Context = context
    fun eventHandler() {
        Toast.makeText(myContext, "Welcome to Data Binding!", Toast.LENGTH_LONG).show()
    }
}