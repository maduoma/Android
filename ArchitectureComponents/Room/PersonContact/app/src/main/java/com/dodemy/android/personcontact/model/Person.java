package com.dodemy.android.personcontact.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String email;
    private String number;
    private String pincode;
    private String city;

    @Ignore
    public Person(String name, String email, String number, String pincode, String city) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.pincode = pincode;
        this.city = city;
    }

    public Person(int id, String name, String email, String number, String pincode, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.pincode = pincode;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
