package com.dodemy.todolistwithroomtesting.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY created DESC")
    fun getAllTodos(): LiveData<List<Todo>>

    @Query("SELECT count(*) FROM todo WHERE completed = 0 AND dueDate >= :date")
    fun getDateCount(date: Long): LiveData<Int>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodo(id: String): Todo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Query("UPDATE todo set completed = ~completed WHERE id = :id")
    fun toggleTodo(id: String): Int
}