package com.dodemy.todolistwithroomtesting.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoRoomDatabase : RoomDatabase() {

    abstract fun todoDao(): ToDoDao

    companion object {
        private var toDoRoomInstance: ToDoRoomDatabase? = null

        fun getInstance(context: Context): ToDoRoomDatabase? {
            if (toDoRoomInstance == null) {
                synchronized(ToDoRoomDatabase::class.java) {
                    if (toDoRoomInstance == null)
                        toDoRoomInstance = Room.databaseBuilder<ToDoRoomDatabase>(
                            context.applicationContext,
                            ToDoRoomDatabase::class.java,
                            "todo_database"
                        )
                            .build()
                }
            }

            return toDoRoomInstance
        }
    }
}