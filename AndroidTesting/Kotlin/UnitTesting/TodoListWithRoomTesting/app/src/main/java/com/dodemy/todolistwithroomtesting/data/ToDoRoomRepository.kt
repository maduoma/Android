package com.dodemy.todolistwithroomtesting.data

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class ToDoRoomRepository(private val toDoDao: ToDoDao) : ToDoRepository {
    private var mAllToDos: LiveData<List<ToDo>> = toDoDao.getAllTodos()

    override fun getUpcomingToDosCount(): LiveData<Int> =
        toDoDao.getDateCount(System.currentTimeMillis())

    override fun getAllToDos(): LiveData<List<ToDo>> {
        return mAllToDos
    }

    override fun insert(toDo: ToDo) {
        require(toDo.title != "") { "Title must not be empty" }
        InsertAsyncTask(toDoDao).execute(toDo)
    }

    override fun toggleTodo(id: String) {
        ToggleAsyncTask(toDoDao).execute(id)
    }

    private class InsertAsyncTask(val toDoDao: ToDoDao) : AsyncTask<ToDo, Unit, Unit>() {
        override fun doInBackground(vararg toDos: ToDo?) {
            toDoDao.insert(toDos[0]!!)
        }
    }

    private class ToggleAsyncTask(val toDoDao: ToDoDao) : AsyncTask<String, Unit, Unit>() {
        override fun doInBackground(vararg ids: String?) {
            require(toDoDao.toggleTodo(ids[0]!!) == 1) { "ToDo not found" }
        }
    }
}