package com.dodemy.android.personcontact.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dodemy.android.personcontact.model.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM PERSON ORDER BY ID")
    List<Person> loadAllPersons();

    @Insert
    void insertPerson(Person person);

    @Update
    void updatePerson(Person person);

    @Delete
    void delete(Person person);

    @Query("SELECT * FROM PERSON WHERE id = :id")
    Person loadPersonById(int id);
}
