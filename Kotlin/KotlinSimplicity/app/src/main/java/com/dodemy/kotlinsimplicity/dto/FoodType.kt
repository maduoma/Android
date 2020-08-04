package com.dodemy.kotlinsimplicity.dto

data class FoodType (var type:String = "Undefined") {
    override fun toString() = type
}
