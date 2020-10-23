package com.dodemy.todolistwithroomtesting

import android.app.Application
import com.dodemy.todolistwithroomtesting.data.TodoRepository
import com.dodemy.todolistwithroomtesting.data.TodoRoomRepository
import com.dodemy.todolistwithroomtesting.data.TodoRoomDatabase

class TodoApplication : Application() {

    val todoRepository: TodoRepository
        get() = TodoRoomRepository(TodoRoomDatabase.getInstance(this.applicationContext)!!.todoDao())
}