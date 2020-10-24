package com.dodemy.todoinjavawithroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.dodemy.todoinjavawithroom.database.AppDatabase

// COMPLETED (1) Make this class extend ViewModel ViewModelProvider.NewInstanceFactory
class AddTaskViewModelFactory     // COMPLETED (3) Initialize the member variables in the constructor with the parameters received
(// COMPLETED (2) Add two member variables. One for the database and one for the taskId
        private val mDb: AppDatabase?, private val mTaskId: Int) : NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddTaskViewModel(mDb, mTaskId) as T
    }
}