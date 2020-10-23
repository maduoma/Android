package com.dodemy.todolistwithroomtesting.data

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class TodoRoomRepository(private val todoDao: TodoDao) : TodoRepository {
    private var allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    override fun getUpcomingTodosCount(): LiveData<Int> =
        todoDao.getDateCount(System.currentTimeMillis())

    override fun getAllTodos(): LiveData<List<Todo>> {
        return allTodos
    }

    override fun insert(todo: Todo) {
        require(todo.title != "") { "Title must not be empty" }
        InsertAsyncTask(todoDao).execute(todo)
    }

    override fun toggleTodo(id: String) {
        ToggleAsyncTask(todoDao).execute(id)
    }

    private class InsertAsyncTask(val todoDao: TodoDao) : AsyncTask<Todo, Unit, Unit>() {
        override fun doInBackground(vararg todos: Todo?) {
            todoDao.insert(todos[0]!!)
        }
    }

    private class ToggleAsyncTask(val todoDao: TodoDao) : AsyncTask<String, Unit, Unit>() {
        override fun doInBackground(vararg ids: String?) {
            require(todoDao.toggleTodo(ids[0]!!) == 1) { "Todo not found" }
        }
    }
}