package com.dodemy.todolistwithroomtesting.data

import androidx.lifecycle.LiveData


interface ToDoRepository {

    fun getAllToDos(): LiveData<List<ToDo>>

    fun insert(toDo: ToDo)

    fun toggleTodo(id: String)

    fun getUpcomingToDosCount(): LiveData<Int>
}