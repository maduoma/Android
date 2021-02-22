package com.dodemy.githubarchitecturecomponents.database;



import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.dodemy.githubarchitecturecomponents.database.converter.DateConverter;
import com.dodemy.githubarchitecturecomponents.database.dao.UserDao;
import com.dodemy.githubarchitecturecomponents.database.entity.User;

//@Database(entities = { YourEntity.class }, version = 1, exportSchema = false)
@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile MyDatabase INSTANCE;

    // --- DAO ---
    public abstract UserDao userDao();
}
