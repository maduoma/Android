package com.dodemy.todoinjavawithroom.database

import androidx.lifecycle.LiveData

class TasksRepository(var database: AppDatabase) {
    private var tasks: LiveData<List<TaskEntry?>?>? = null
    private val taskDao: TaskDao? = null
    fun getLoadAllTasks(): LiveData<List<TaskEntry?>?>? {
        tasks = database.taskDao().loadAllTasks()
        return tasks
    }

    fun getLoadTaskById(taskId: Int): LiveData<TaskEntry?>? {
        return database.taskDao().loadTaskById(taskId)
    }

    fun deleteTasks(taskEntry: TaskEntry?) {
        database.taskDao().deleteTask(taskEntry)
    }

    fun updateTaskById(task: TaskEntry?) {
        database.taskDao().updateTask(task)
    }

    companion object {
        private val LOG_TAG = TasksRepository::class.java.simpleName
    }
}