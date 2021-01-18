package com.dodemy.personinforoom.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "person")
class Person {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String
    var email: String
    var number: String
    var pinCode: String
    var city: String

    @Ignore
    constructor(name: String, email: String, number: String, pinCode: String, city: String) {
        this.name = name
        this.email = email
        this.number = number
        this.pinCode = pinCode
        this.city = city
    }

    constructor(
            id: Int,
            name: String,
            email: String,
            number: String,
            pinCode: String,
            city: String
    ) {
        this.id = id
        this.name = name
        this.email = email
        this.number = number
        this.pinCode = pinCode
        this.city = city
    }
}
