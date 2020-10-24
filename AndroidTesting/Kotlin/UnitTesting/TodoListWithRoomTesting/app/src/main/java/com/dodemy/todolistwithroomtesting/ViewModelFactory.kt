package com.dodemy.todolistwithroomtesting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dodemy.todolistwithroomtesting.add.AddViewModel
import com.dodemy.todolistwithroomtesting.data.ToDoRepository
import com.dodemy.todolistwithroomtesting.list.ListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val toDoRepository: ToDoRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(ListViewModel::class.java) ->
                    ListViewModel(toDoRepository)
                isAssignableFrom(AddViewModel::class.java) ->
                    AddViewModel(toDoRepository)
                else ->
                    throw IllegalArgumentException("ViewModel class (${modelClass.name}) is not mapped")
            }
        } as T
}

