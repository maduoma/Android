package com.dodemy.contactwithroom.viewmodel;


import android.app.Application;

import android.os.AsyncTask;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;

import com.dodemy.contactwithroom.repository.PersonRepository;
import com.dodemy.contactwithroom.model.Person;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PersonViewModel extends AndroidViewModel {

    private PersonRepository personRepository = new PersonRepository(this.getApplication());
    private final Executor executor = Executors.newFixedThreadPool(2);

    private final MediatorLiveData<List<Person>> personsByCity = new MediatorLiveData<>();

    private final MediatorLiveData<String> mobileNo = new MediatorLiveData<>();

    //transformation applied so that observer to this LiveData can be added only once
    private final LiveData<Person> personsByMobile = Transformations.switchMap(mobileNo, (mobile) -> {
        return personRepository.getPersonByMobile(mobile);
    });

    public LiveData<List<Person>> getPersonsByCityLive(){
        return personsByCity;
    }
    public PersonViewModel(@NonNull Application application){
        super(application);
    }

    //Room DAO call needs to be run on background thread
    //This example uses Executor
    public void addPerson(Person p){
        executor.execute(() -> {
            personRepository.addPerson(p);
        });
    }
    public void updatePerson(Person p){
        executor.execute(() -> {
        personRepository.updatePerson(p);
        });
    }
    public void deletePerson(Person p){
        executor.execute(() -> {
        personRepository.deletePerson(p);
        });
    }
    //Since room DAO returns LiveData, it runs on background thread.
    public LiveData<List<Person>> getAllPersons(){
        return personRepository.getAllPersons();
    }



    //Room DAO call needs to be run on background thread
    //This example uses AsyncTask
    public void getPersonsByCity(List<String> cities){
        new AsyncTask<Void, Void, List<Person>>() {
            @Override
            protected List<Person> doInBackground(Void... params) {
                return personRepository.getPersonsByCity(cities);
            }
            @Override
            protected void onPostExecute(List<Person> personLst) {
                Log.d("", "persons by city "+personLst.size());
                personsByCity.setValue(personLst);
            }
        }.execute();

    }
    //sets the mobile number to LiveData object,
    //transformation using switchMap intern calls room DAO method which return LiveData
    public void setMobile(String mobile){
        mobileNo.setValue(mobile);
    }
    public LiveData<Person> getPersonsByMobile(){
        return personsByMobile;
    }
}
