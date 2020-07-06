package com.dodemy.android.androidroomtodolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    long insertTodo(Todo todo);

    @Insert
    void insertTodoList(List<Todo> todoList);

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO)
    List<Todo> fetchAllTodos();

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO + " WHERE category = :category")
    List<Todo> fetchTodoListByCategory(String category);

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO + " WHERE todo_id = :todoId")
    Todo fetchTodoListById(int todoId);

    @Update
    int updateTodo(Todo todo);

    @Delete
    int deleteTodo(Todo todo);
}
