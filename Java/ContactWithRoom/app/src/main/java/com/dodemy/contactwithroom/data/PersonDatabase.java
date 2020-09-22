package com.dodemy.contactwithroom.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dodemy.contactwithroom.data.PersonDAO;
import com.dodemy.contactwithroom.model.Person;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class PersonDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 1;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract PersonDAO PersonDatabase();
}
