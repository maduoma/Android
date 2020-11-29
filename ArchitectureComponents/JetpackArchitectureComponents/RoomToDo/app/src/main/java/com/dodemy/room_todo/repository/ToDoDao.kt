package com.dodemy.room_todo.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dodemy.room_todo.model.ToDo


@Dao
interface ToDoDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo (toDo: ToDo)

    @Update
    suspend fun updateToDo (toDo: ToDo)

    @Delete
    suspend fun deleteToDo (toDo: ToDo)

    @Query("SELECT * FROM ToDo")
    fun getAllToDo(): LiveData<List<ToDo>>
}