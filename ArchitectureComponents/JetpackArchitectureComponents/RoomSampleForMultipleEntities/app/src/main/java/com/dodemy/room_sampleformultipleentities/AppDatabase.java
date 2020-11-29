package com.dodemy.room_sampleformultipleentities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**************************************
 *Creating the database
 * We need to define an abstract class that extends RoomDatabase. This class is annotated with @Database, lists the entities contained in the database, and the DAOs which access them.
 */
@Database(entities = {Accessory.class, Answer.class, Avatar.class, Clothes.class, Hair.class, Points.class, Question.class, Scenario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract AccessoryDao accessoryDao();
}