package com.dodemy.android.todolistusingroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public static final String DB_NAME = "app_db";
    public static final String TABLE_NAME_TODO = "todo";

    public abstract DaoAccess daoAccess();

}
