package com.dodemy.todoinjavawithroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dodemy.todoinjavawithroom.database.AppDatabase
import com.dodemy.todoinjavawithroom.database.TaskEntry
import com.dodemy.todoinjavawithroom.database.TasksRepository

class AddTaskViewModel(database: AppDatabase?, taskId: Int) : ViewModel() {
    // COMPLETED (7) Create a getter for the task variable
    // COMPLETED (6) Add a task member variable for the TaskEntry object wrapped in a LiveData
    val task: LiveData<TaskEntry?>?
    private val tasksRepository: TasksRepository = TasksRepository(database!!)
    fun updateTask(task: TaskEntry?) {
        tasksRepository.updateTaskById(task)
    }

    // COMPLETED (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    init {
        // task = database.taskDao().loadTaskById(taskId);
        task = tasksRepository.getLoadTaskById(taskId)
    }
}