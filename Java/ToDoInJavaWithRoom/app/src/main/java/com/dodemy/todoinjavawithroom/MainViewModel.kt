package com.dodemy.todoinjavawithroom

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dodemy.todoinjavawithroom.database.AppDatabase.Companion.getInstance
import com.dodemy.todoinjavawithroom.database.TaskEntry
import com.dodemy.todoinjavawithroom.database.TasksRepository

class MainViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val tasks: LiveData<List<TaskEntry?>?>?
    private val tasksRepository: TasksRepository
    fun deleteTask(taskEntry: TaskEntry?) {
        tasksRepository.deleteTasks(taskEntry)
    }

    companion object {
        // Constant for logging
        private val TAG = MainViewModel::class.java.simpleName
    }

    init {
        val database = getInstance(getApplication())
        Log.d(TAG, "Actively retrieving the tasks from the DataBase")
        //tasks = database.taskDao().loadAllTasks();
        tasksRepository = TasksRepository(database!!)
        tasks = tasksRepository.getLoadAllTasks()
    }
}