package com.dodemy.todolistwithroomtesting.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoRoomDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        private var todoRoomInstance: TodoRoomDatabase? = null

        fun getInstance(context: Context): TodoRoomDatabase? {
            if (todoRoomInstance == null) {
                synchronized(TodoRoomDatabase::class.java) {
                    if (todoRoomInstance == null)
                        todoRoomInstance = Room.databaseBuilder<TodoRoomDatabase>(
                            context.applicationContext,
                            TodoRoomDatabase::class.java,
                            "todo_database"
                        )
                            .build()
                }
            }

            return todoRoomInstance
        }
    }
}