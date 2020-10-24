package com.dodemy.todolistwithroomtesting.add

import androidx.lifecycle.ViewModel
import com.dodemy.todolistwithroomtesting.data.ToDo
import com.dodemy.todolistwithroomtesting.data.ToDoRepository
import java.util.*

class AddViewModel(
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    val todo = ToDo(
        UUID.randomUUID().toString(),
        "",
        null,
        false,
        0
    )


    fun save(): String? {
        if (todo.title == "") return "Title is required"

        todo.created = System.currentTimeMillis()
        toDoRepository.insert(todo)
        return null
    }

}