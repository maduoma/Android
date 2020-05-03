package com.dodemy.android.crudwithroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class ItemRoomDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();

    private static volatile ItemRoomDatabase INSTANCE;

    public static ItemRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (ItemRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemRoomDatabase.class,
                            "item_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
