package com.dodemy.databinding

import android.content.Context
import android.widget.Toast

open class EventHandler(context: Context) {
    private val myContext: Context = context
    fun eventHandler() {
        val greetings = "Welcome to Data Binding!"
        Toast.makeText(myContext, greetings, Toast.LENGTH_LONG).show()
    }
}

/*
class EventHandler() {
    private var context: Context? = null
    constructor(context: Context) : this() {
        this.context = context
    }
 */