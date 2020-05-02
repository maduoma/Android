package com.dodemy.android.crudwithroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void insertItem(Item item);

    @Query("SELECT * FROM item_table")
    List<Item> getAllItems();

    @Delete
    void delete(Item item);
}
