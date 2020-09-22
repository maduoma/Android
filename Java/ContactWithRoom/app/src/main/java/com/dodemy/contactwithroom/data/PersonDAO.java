package com.dodemy.contactwithroom.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dodemy.contactwithroom.model.Person;

import java.util.List;

@Dao
public interface PersonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPerson(Person person);

    @Update
    void updatePerson(Person person);

    @Delete
    void deletePerson(Person person);

    @Query("SELECT * FROM person")
    LiveData<List<Person>> getAllPersons();

    @Query("SELECT * FROM person where mobile = :mobileIn")
    LiveData<Person> getPersonByMobile(String mobileIn);

    @Query("SELECT * FROM person where city In (:cityIn)")
    List<Person> getPersonByCities(List<String> cityIn);
}
