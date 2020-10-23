package com.dodemy.todolistwithroomtesting.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dodemy.todolistwithroomtesting.data.TodoRepository
import com.dodemy.todolistwithroomtesting.data.Todo


class ListViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val allTodos: LiveData<List<Todo>> = todoRepository.getAllTodos()
    val upcomingTodosCount: LiveData<Int> = todoRepository.getUpcomingTodosCount()

    fun toggleTodo(id: String) {
        todoRepository.toggleTodo(id)
    }

}