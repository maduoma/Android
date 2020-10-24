package com.dodemy.todolistwithroomtesting.add

import androidx.lifecycle.ViewModel
import com.dodemy.todolistwithroomtesting.data.ToDo
import com.dodemy.todolistwithroomtesting.data.ToDoRepository
import java.util.*

class AddViewModel(
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    val toDo = ToDo(
        UUID.randomUUID().toString(),
        "",
        null,
        false,
        0
    )


    fun save(): String? {
        if (toDo.title == "") return "Title is required"

        toDo.created = System.currentTimeMillis()
        toDoRepository.insert(toDo)
        return null
    }

}