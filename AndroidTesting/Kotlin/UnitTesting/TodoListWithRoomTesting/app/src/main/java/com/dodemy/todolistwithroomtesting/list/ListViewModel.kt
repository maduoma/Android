package com.dodemy.todolistwithroomtesting.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dodemy.todolistwithroomtesting.data.ToDoRepository
import com.dodemy.todolistwithroomtesting.data.ToDo


class ListViewModel(
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    val mAllToDos: LiveData<List<ToDo>> = toDoRepository.getAllToDos()
    val upcomingToDosCount: LiveData<Int> = toDoRepository.getUpcomingToDosCount()

    fun toggleTodo(id: String) {
        toDoRepository.toggleTodo(id)
    }

}