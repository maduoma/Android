package com.dodemy.room_sampleformultipleentities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**********************************************
 * Creating DAO class
 *DAOs are responsible for defining the methods that access the database. In the initial SQLite implementation of our project, all the queries to the database were done in the LocalUserDataSource file, where we were working with Cursor objects. With Room, we don’t need all the Cursor related code and can simply define our queries using annotations in the UserDao class.
 */

@Dao
public interface AccessoryDao {

    @Insert
    void insertAllAccessories(List<Accessory> hairsList);

    @Query("SELECT accessoryPurchased FROM accessories WHERE accessoryId = :id")
    int getPurchasedAccessories(int id);

    @Query("UPDATE accessories SET accessoryPurchased = 1 WHERE accessoryId = :id")
    void setPurchasedAccessories(int id);

    @Query("UPDATE  Accessories SET accessoryPurchased =0")
    void resetAccessoryPurchase();

}