package com.dodemy.todolistwithroomtesting.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo ORDER BY created DESC")
    fun getAllToDos(): LiveData<List<ToDo>>

    @Query("SELECT count(*) FROM todo WHERE completed = 0 AND dueDate >= :date")
    fun getDateCount(date: Long): LiveData<Int>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getToDo(id: String): ToDo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toDo: ToDo)

    @Query("UPDATE todo set completed = ~completed WHERE id = :id")
    fun toggleTodo(id: String): Int
}