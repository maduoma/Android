package com.dodemy.todolistwithroomtesting

import android.app.Application
import com.dodemy.todolistwithroomtesting.data.ToDoRepository
import com.dodemy.todolistwithroomtesting.data.ToDoRoomRepository
import com.dodemy.todolistwithroomtesting.data.ToDoRoomDatabase

class ToDoApplication : Application() {

    val toDoRepository: ToDoRepository
        get() = ToDoRoomRepository(ToDoRoomDatabase.getInstance(this.applicationContext)!!.toDoDao())
}