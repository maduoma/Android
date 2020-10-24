package com.dodemy.todoinjavawithroom.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY priority")
    fun loadAllTasks(): LiveData<List<TaskEntry?>?>?

    @Query("SELECT * FROM task WHERE id=:id")
    fun loadTaskById(id: Int): LiveData<TaskEntry?>?

    @Insert
    fun insertTask(taskEntry: TaskEntry?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(taskEntry: TaskEntry?)

    @Delete
    fun deleteTask(taskEntry: TaskEntry?)
}