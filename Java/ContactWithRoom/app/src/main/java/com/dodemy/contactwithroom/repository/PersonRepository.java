package com.dodemy.contactwithroom.repository;



import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.dodemy.contactwithroom.data.DatabaseCreator;
import com.dodemy.contactwithroom.data.PersonDAO;
import com.dodemy.contactwithroom.model.Person;

import java.util.List;

public class PersonRepository {

    private final PersonDAO personDAO;

    public PersonRepository(Context context) {
        personDAO = DatabaseCreator.getPersonDatabase(context).PersonDatabase();
    }

    public void addPerson(Person p) {
           long rec =  personDAO.insertPerson(p);
        Log.d("db insert ","added "+rec);
    }

    public void updatePerson(Person p) {
        personDAO.updatePerson(p);
    }

    public void deletePerson(Person p) {
        personDAO.deletePerson(p);
    }

    public LiveData<List<Person>> getAllPersons() {
        return personDAO.getAllPersons();
    }

    public List<Person> getPersonsByCity(List<String> cities) {
        return personDAO.getPersonByCities(cities);
    }

    public LiveData<Person> getPersonByMobile(String mobile) {
        return personDAO.getPersonByMobile(mobile);
    }
}
