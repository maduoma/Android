package com.dodemy.todolistwithroomtesting.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(

    @PrimaryKey var id: String,
    var title: String,
    var dueDate: Long?,
    var completed: Boolean,
    var created: Long
)