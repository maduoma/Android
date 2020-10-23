package com.dodemy.todolistwithroomtesting.data

import androidx.lifecycle.LiveData


interface TodoRepository {

    fun getAllTodos(): LiveData<List<Todo>>

    fun insert(todo: Todo)

    fun toggleTodo(id: String)

    fun getUpcomingTodosCount(): LiveData<Int>
}