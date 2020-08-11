package com.dodemy.junittestclass;

public class Person {
    private String mName;
    private int mAge;
    public Person(String name, int age) {
        mName = name;
        mAge = age;
    }
    @Override
    public boolean equals(Object o) {
        Person other = (Person) o;
        return mName.equals(other.mName) && mAge == other.mAge;
    }
}
